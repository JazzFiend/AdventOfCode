module Day01SecretEntrance.CombinationLock where

import Data.List (foldl')
import Day01SecretEntrance.Move (Move, applyMove, parseMove)

initialDial :: Int
initialDial = 50

runCombinationFinalNumber :: [String] -> Int
runCombinationFinalNumber [] = initialDial
runCombinationFinalNumber turnList = foldl' applyMove initialDial moves
  where
    moves = map parseMove turnList

runCombinationCountNumber :: [String] -> Int -> Int
runCombinationCountNumber turnList trackingNumber =
  length $ filter (== trackingNumber) allPositions
  where
    allPositions = scanl applyMove initialDial moves
    moves = map parseMove turnList