module CombinationLockWithTracker
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
turnDial lockState turn =
  LockState
    { position = calculatedPosition,
      zeroCount = zeroCount lockState + zeroCountIncrement,
      zeroPassCount = zeroPassCount lockState + zeroPassIncrement
    }
  where
    totalPosition = position lockState + parseTurn turn
    calculatedPosition = totalPosition `mod` 100
    zeroCountIncrement =
      if calculatedPosition == 0
        then 1
        else 0
    zeroPassIncrement =
      (if isDoubleZero (position lockState) totalPosition then -1 else 0) + calculateZeroPasses totalPosition

parseTurn :: String -> Int
parseTurn turn =
  if take 1 turn == "R"
    then read (drop 1 turn)
    else (-1) * read (drop 1 turn)

isDoubleZero :: Int -> Int -> Bool
isDoubleZero previousPosition currentPosition =
  previousPosition == 0 && currentPosition < 0

calculateZeroPasses :: Int -> Int
calculateZeroPasses n
  | n == 0 || n == 100 = 1
  | n > 100 = 1 + calculateZeroPasses (n - 100)
  | n < 0 = 1 + calculateZeroPasses (n + 100)
  | otherwise = 0
