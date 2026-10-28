module Day02.ProductValidator
  ( isValidProduct,
  )
where

isValidProduct :: String -> Bool
isValidProduct id
  | null id = False
  | length id == 1 = True
  | otherwise =
      (head id /= (id !! 1)) && isValidProduct (tail id)