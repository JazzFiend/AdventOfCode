module Day01SecretEntrance.CombinationLock where

initialDial :: Int
initialDial = 50

applyMove :: Int -> String -> Int
applyMove current "" = current
applyMove current ('R' : distance) = (current + read distance) `mod` 100
applyMove current ('L' : distance) = (current - read distance) `mod` 100
applyMove _ badInput = error ("Invalid direction: " ++ badInput ++ ". Expected an L or R.")

runCombinationFinalNumber :: [String] -> Int
runCombinationFinalNumber = foldl applyMove initialDial

runCombinationCountNumber :: [String] -> Int -> Int
runCombinationCountNumber turnList trackingNumber = 
    length $ filter (== trackingNumber) allPositions
  where
    allPositions = scanl applyMove initialDial turnList