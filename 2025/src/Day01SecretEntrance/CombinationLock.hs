module Day01SecretEntrance.CombinationLock where

runCombinationFinalNumber :: String -> Int
runCombinationFinalNumber "" = 50
runCombinationFinalNumber ('R' : distance) = 50 + read distance
runCombinationFinalNumber ('L' : distance) = 50 - read distance
runCombinationFinalNumber badInput = error ("Invalid direction: " ++ badInput ++ ". Expected an L or R.")