module Day01SecretEntrance.CombinationLock where

initialDial :: Int
initialDial = 50

runCombinationFinalNumber :: [String] -> Int
runCombinationFinalNumber = foldl applyMove initialDial
  where
    applyMove current "" = current
    applyMove current ('R' : distance) = (current + read distance) `mod` 100
    applyMove current ('L' : distance) = (current - read distance) `mod` 100
    applyMove _ badInput = error ("Invalid direction: " ++ badInput ++ ". Expected an L or R.")

runCombinationCountNumber :: [String] -> Int -> Int
runCombinationCountNumber _ trackingNumber = if trackingNumber == initialDial then 1 else 0