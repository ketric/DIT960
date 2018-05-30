{-# OPTIONS -Wall #-}

{-
  to compile and run:
  $ ghc -O -main-is CheckInsert.main CheckInsert.hs && ./CheckInsert
-}

--------------------------------------------------------------------------------

module CheckInsert where

import Data.List ( permutations, foldl', sort )
import Control.Monad ( when )
import AATree

--------------------------------------------------------------------------------
-- check insertion

main :: IO ()
main = do
  when (not $ checkTree (emptyTree::AATree Int)) $
    fail "Invariant not true for empty tree"
  mapM_ checkTreesOfSize [1..10]

checkTreesOfSize :: Int -> IO ()
checkTreesOfSize n = do
  putStrLn ("Testing trees of size " ++ show n)
  mapM_ checkTreeFromList (permutations [1..n])

checkTreeFromList :: [Int] -> IO ()
checkTreeFromList list
  | checkTree tree' && inorder tree' == sort list = return ()
  | otherwise = do
    putStrLn ("\nInvariant not true after inserting\n  " ++ show (head list) ++
              "\ninto\n  " ++ show tree ++
              "\nresulting tree was\n  " ++ show tree')
    fail "bad tree"
  where tree = foldl' (flip insert) emptyTree (tail list)
        tree' = insert (head list) tree

--------------------------------------------------------------------------------
