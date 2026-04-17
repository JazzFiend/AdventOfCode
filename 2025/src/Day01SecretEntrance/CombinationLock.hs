module Day01SecretEntrance.CombinationLock where

runCombinationFinalNumber :: String -> Int
runCombinationFinalNumber "" = 50
runCombinationFinalNumber ('R' : distance) = (50 + read distance) `mod` 100
runCombinationFinalNumber ('L' : distance) = (50 - read distance) `mod` 100
runCombinationFinalNumber badInput = error ("Invalid direction: " ++ badInput ++ ". Expected an L or R.")