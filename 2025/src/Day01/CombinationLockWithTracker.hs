module Day01.CombinationLockWithTracker
  ( LockState (..),
    runCombination,
  )
where

import Data.List (foldl')

data LockState = LockState
  { position :: Int,
    zeroCount :: Int,
    zeroPassCount :: Int
  }
  deriving (Eq, Show)

initialState :: LockState
initialState = LockState {position = 50, zeroCount = 0, zeroPassCount = 0}

runCombination :: [String] -> LockState
runCombination = foldl' turnDial initialState

turnDial :: LockState -> String -> LockState
turnDial (LockState position zeroCount zeroPassCount) turn =
  LockState
    { position = calculatedPosition,
      zeroCount = zeroCount + zeroCountIncrement,
      zeroPassCount = zeroPassCount + zeroPassIncrement
    }
  where
    totalPosition = position + parseTurn turn
    calculatedPosition = totalPosition `mod` 100
    zeroCountIncrement = fromEnum (calculatedPosition == 0)
    zeroPassIncrement =
      (if isDoubleZero position totalPosition then -1 else 0) + calculateZeroPasses totalPosition

parseTurn :: String -> Int
parseTurn ('R' : number) = read number
parseTurn ('L' : number) = -read number
parseTurn invalid = error $ "Invalid input: " ++ invalid

isDoubleZero :: Int -> Int -> Bool
isDoubleZero previousPosition currentPosition =
  previousPosition == 0 && currentPosition < 0

calculateZeroPasses :: Int -> Int
calculateZeroPasses n
  | n == 0 = 1
  | n >= 100 = n `div` 100
  | n < 0 = 1 + abs n `div` 100
  | otherwise = 0
