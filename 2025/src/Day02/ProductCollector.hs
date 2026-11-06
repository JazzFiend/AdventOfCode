module Day02.ProductCollector where

import Data.List (nub)
import Data.List.Split (splitOn)

parseProducts :: String -> [Int]
parseProducts productRange =
  if null productRange
    then []
    else result
  where
    endpoints = splitOn "-" productRange
    start = read (head endpoints) :: Int
    end = read (last endpoints) :: Int
    result = nub [start .. end]
