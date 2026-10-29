module Day02.ProductValidator
  ( isValidProduct,
  )
where

isValidProduct :: String -> Bool
isValidProduct id =
  not (null id) && all (containsDuplication id) sizesToCheck
  where
    maxCheckSize = length id
    sizesToCheck = [1 .. maxCheckSize]

containsDuplication :: String -> Int -> Bool
containsDuplication id checkSize
  | length id <= checkSize = True
  | otherwise =
      isNumberRepeated id checkSize && containsDuplication (drop checkSize id) checkSize

isNumberRepeated :: String -> Int -> Bool
isNumberRepeated id checkSize = take checkSize id /= take checkSize (drop checkSize id)