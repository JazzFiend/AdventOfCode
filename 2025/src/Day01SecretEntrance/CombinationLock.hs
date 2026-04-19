module Day01SecretEntrance.CombinationLock where

runCombinationFinalNumber :: [String] -> Int
runCombinationFinalNumber = foldl applyMove 50
  where
    applyMove current "" = current
    applyMove current ('R' : distance) = (current + read distance) `mod` 100
    applyMove current ('L' : distance) = (current - read distance) `mod` 100
    applyMove _ badInput = error ("Invalid direction: " ++ badInput ++ ". Expected an L or R.")

runCombinationCountNumber :: [String] -> Int -> Int
runCombinationCountNumber _ _ = 0