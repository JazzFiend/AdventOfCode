module Day02.ProductValidator
  ( isValidProduct,
  )
where

isValidProduct :: String -> Bool
isValidProduct id =
  not (null id) && not (any (containsDuplication id) sizesToCheck)
  where
    maxCheckSize = length id
    sizesToCheck = [1 .. maxCheckSize]

containsDuplication :: String -> Int -> Bool
containsDuplication id checkSize
  | length id <= checkSize = False
  | isNumberRepeated id checkSize =
      (length nextId <= checkSize) || containsDuplication nextId checkSize
  | otherwise = False
  where
    nextId = drop checkSize id

isNumberRepeated :: String -> Int -> Bool
isNumberRepeated id checkSize = take checkSize id == take checkSize (drop checkSize id)
