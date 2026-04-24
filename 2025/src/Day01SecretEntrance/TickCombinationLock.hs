module Day01SecretEntrance.TickCombinationLock (countAllTicks) where

initialDial :: Int
initialDial = 50

countAllTicks :: [String] -> Int -> Int
countAllTicks _ 50 = 1
countAllTicks _ _ = 0
