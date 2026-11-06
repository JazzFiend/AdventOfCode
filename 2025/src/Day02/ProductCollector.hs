module Day02.ProductCollector where

parseProducts :: String -> [String]
parseProducts productRange =
  [drop 2 productRange | not (null productRange)]