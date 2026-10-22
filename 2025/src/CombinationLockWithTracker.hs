module CombinationLockWithTracker
  ( LockState (..),
    runCombination,
  )
where

import Data.List (foldl')

data LockState = LockState
  { position :: Int,
    zeroCount :: Int
  }
  deriving (Eq, Show)

initialState :: LockState
initialState = LockState {position = 50, zeroCount = 0}

runCombination :: [String] -> LockState
runCombination = foldl' turnDial initialState

turnDial :: LockState -> String -> LockState
turnDial lockState turn = LockState {position = calculatedPosition, zeroCount = zeroCount lockState + zeroCountIncrement}
  where
    calculatedPosition = (position lockState + parseTurn turn) `mod` 100
    zeroCountIncrement =
      if calculatedPosition == 0
        then 1
        else 0

parseTurn :: String -> Int
parseTurn turn =
  if take 1 turn == "R"
    then read (drop 1 turn)
    else (-1) * read (drop 1 turn)
