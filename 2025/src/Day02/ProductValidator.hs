module Day02.ProductValidator where

isValidProduct :: String -> Bool
isValidProduct id
  | null id = False
  | length id == 1 = True
  | otherwise = head id /= last id
