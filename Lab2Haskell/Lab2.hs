import Control.Applicative
import Data.Maybe
import Data.Ord
import System.Environment
import System.IO

import SkewHeap

-- | Bids.

data Bid
  = Buy Person Price           -- Person offers to buy share
  | Sell Person Price          -- Person offers to sell share
  | NewBuy Person Price Price  -- Person changes buy bid
  | NewSell Person Price Price -- Person changes sell bid
  deriving Show

-- |Type for bids in a book, contains bid value and name of bidder.
--
-- SellBids are min ordered (ascending).
--
-- BuyBids are max ordered (descending).
-- This means comparison is reverse to whats "intuitive" for integers,
-- as our heap is a min heap. NOTE: Possibly a better solution to have
-- the heap creatable with either min or max heap property.
data BidType
    = SellBid Price Person
    | BuyBid Price Person
    deriving Eq
instance Ord BidType where
    compare (SellBid a _) (SellBid b _) = compare a b
    compare (BuyBid a _) (BuyBid b _) = compare b a
instance Show BidType where
    show (SellBid price name) = name ++ " " ++ show price
    show (BuyBid price name) = name ++ " " ++ show price

-- |Book of buybids (first heap) and sellbids (second heap).
data OrderBook =
    OrderBook (SkewHeap BidType) (SkewHeap BidType)
    deriving Show

type Person = String
type Price = Integer

-- | Parses a bid. Incorrectly formatted bids are returned verbatim
-- (tagged with 'Left').

parseBid :: String -> Either String Bid
parseBid s = case words s of
  name : kind : prices ->
    case (kind, mapM readInteger prices) of
      ("K",  Just [price])              -> Right (Buy name price)
      ("S",  Just [price])              -> Right (Sell name price)
      ("NK", Just [oldPrice, newPrice]) -> Right (NewBuy name oldPrice newPrice)
      ("NS", Just [oldPrice, newPrice]) -> Right (NewSell name oldPrice newPrice)
      _ -> Left s
  _ -> Left s
  where
  readInteger :: String -> Maybe Integer
  readInteger s = case filter (null . snd) $ reads s of
    [(x, _)] -> Just x
    _        -> Nothing

-- | Parses a sequence of bids. Correctly formatted bids are returned
-- (in the order encountered), and an error message is printed for
-- each incorrectly formatted bid.

parseBids :: String -> IO [Bid]
parseBids s = concat <$> mapM (check . parseBid) (lines s)
  where
  check (Left bid)  = do
    hPutStrLn stderr $ "Malformed bid: " ++ bid
    return []
  check (Right bid) = return [bid]

-- | The main function of the program.

main :: IO ()
main = do
  args <- getArgs
  case args of
    []  -> process stdin
    [f] -> process =<< openFile f ReadMode
    _   -> hPutStr stderr $ unlines
      [ "Usage: ./Lab2 [<file>]"
      , "If no file is given, then input is read from standard input."
      ]
  where
  process h = trade =<< parseBids =<< hGetContents h

-- | The core of the program. Takes a list of bids and executes them.

trade :: [Bid] -> IO ()
trade bids = innerTrade bids (OrderBook Null Null)
  where
    innerTrade [] (OrderBook buy sell)          = do
        putStrLn "Order book:"
        putStrLn $ "Sellers: " ++ commaSeparatedStr sell
        putStrLn $ "Buyers: " ++ commaSeparatedStr buy
    innerTrade (b:bs) book@(OrderBook buy sell) = do
        newBook <- tradeWork b book
        innerTrade bs newBook
    
    tradeWork (Buy person price) (OrderBook buy sell)
        | bidCompare price sell = do
            let ((SellBid sellPrice sellName), newSell) = fromJust $ removeMin sell
            putStrLn $ person ++ " buys from " ++ sellName ++ " for " ++ show price ++ "kr"
            return (OrderBook buy newSell)
        | otherwise             =
            return $ OrderBook (insert (BuyBid price person) buy) sell 

    tradeWork (Sell person price) (OrderBook buy sell)
        | bidCompare price buy = do
            let ((BuyBid buyPrice buyName), newBuy) = fromJust $ removeMin buy
            putStrLn $ buyName ++ " buys from " ++ person ++ " for " ++ show price ++ "kr"
            return (OrderBook newBuy sell)
        | otherwise = 
            return $ OrderBook buy (insert (SellBid price person) sell)

    tradeWork (NewBuy person oldprice newprice) (OrderBook buy sell) =
        tradeWork (Buy person newprice) (OrderBook (remove (BuyBid oldprice person) buy) sell)

    tradeWork (NewSell person oldprice newprice) (OrderBook buy sell) =
        tradeWork (Sell person newprice) (OrderBook buy (remove (SellBid oldprice person) sell))

    bidCompare bidA heap =
        case root heap of
            Nothing               -> False
            Just (SellBid bidB _) -> bidA >= bidB
            Just (BuyBid bidB _)  -> bidA <= bidB