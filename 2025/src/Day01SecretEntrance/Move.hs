module Day01SecretEntrance.Move where

data Direction = L | R deriving (Show, Eq)

data Move = Move Direction Int deriving (Show, Eq)

parseMove :: String -> Move
parseMove ('R' : distance) = Move R (read distance)
parseMove ('L' : distance) = Move L (read distance)
parseMove badInput = error ("Invalid direction: " ++ badInput ++ ". Expected an L or R.")

applyMove :: Int -> Move -> Int
applyMove current (Move R distance) = (current + distance) `mod` 100
applyMove current (Move L distance) = (current - distance) `mod` 100
