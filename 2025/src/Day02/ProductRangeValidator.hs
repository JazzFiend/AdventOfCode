module Day02.ProductRangeValidator where

import Data.List (foldl')
import Day02.ProductCollector (parseProducts)
import Day02.ProductValidator (isValidProduct)

-- Now to put it all together.

invalidProductIdSum :: String -> Int
invalidProductIdSum productRanges = sum
  where
    allProductIds = parseProducts productRanges
    invalidProductIds = filter (not . isValidProduct) allProductIds
    sum = foldl' (+) 0 (map read invalidProductIds)
