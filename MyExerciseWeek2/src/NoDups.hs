module NoDups where

import Data.List (sort)
import Test.QuickCheck

-- O(n log n)
noDups :: Ord a => [a] -> [a]
noDups []  = []
noDups [x] = [x]
noDups xs  = noDups ys `merge` noDups zs  -- T(n) = O(n) + 2T(n/2) = O(n log n)
 where
  (ys, zs) = splitAt (n `div` 2) xs  -- O(n)
  n        = length xs               -- O(n)

-- Slightly improved version which calculates length of xs once, samma ordo
noDups' :: Ord a => [a] -> [a]
noDups' xs = rec (length xs) xs
 where
  rec _ []  = []
  rec _ [x] = [x]
  rec len xs  = rec n ys `merge` rec (len - n) zs
   where
    (ys, zs) = splitAt n xs
    n        = len `div` 2

-- T(n) = O(1) + T(n-1) = O(n)  (in case x == y it is T(n-2) but still samma ordo)
merge :: Ord a => [a] -> [a] -> [a]
merge xs [] = xs
merge [] ys = ys
merge (x:xs) (y:ys)
  | x <  y    = x : merge xs (y:ys)
  | x == y    = x : merge xs ys
  | otherwise = y : merge (x:xs) ys

-- O(n log n) + O(n) = O(n log n)
hasDups :: Ord a => [a] -> Bool
hasDups xs = length xs /= length (noDups xs)

-- Sort has O(n log n) (mergesort implementation) and rec is O(n), so O(n log n)
hasDups' :: Ord a => [a] -> Bool
hasDups' = rec . sort
 where
  rec []       = False
  rec [x]      = False
  rec (x:y:ys) | x == y    = True
               | otherwise = rec (y:ys)

-- As a fold with accum parameter
hasDups'' :: Ord a => [a] -> Bool
hasDups'' [] = False
hasDups'' xs = snd $ foldl (\(x, b) y -> (y, x == y || b)) (a, False) as
 where
  (a:as) = sort xs

-- Point free version
hasDups''' :: Ord a => [a] -> Bool
hasDups''' = or . (\(y:ys) -> zipWith (==) (y:ys) ys) . sort

-- Tests

prop_dups :: [Int] -> Bool
prop_dups = not . hasDups' . noDups

prop_has :: [Int] -> [Int] -> Bool
prop_has xs ys = let zs = xs ++ ys ++ xs ++ ys in hasDups zs == hasDups' zs