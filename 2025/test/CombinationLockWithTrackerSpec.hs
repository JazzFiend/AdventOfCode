module CombinationLockWithTrackerSpec (spec) where

import CombinationLockWithTracker
import Test.Hspec

spec :: Spec
spec = describe "CombinationLockWithTracker" $ do
  describe "Default Value" $ do
    it "should be set to 50 by default" $
      runCombination [] `shouldBe` LockState {position = 50, zeroCount = 0}

    describe "Never hit zero" $ do
      describe "One Left move" $ do
        it "should decrement the counter" $
          runCombination ["L1"] `shouldBe` LockState {position = 49, zeroCount = 0}
      describe "One Right move" $ do
        it "should increment the counter" $
          runCombination ["R10"] `shouldBe` LockState {position = 60, zeroCount = 0}
      describe "Multiple moves" $ do
        it "should land at the appropriate number" $
          runCombination ["L10", "R20", "L30"] `shouldBe` LockState {position = 30, zeroCount = 0}
      describe "Right Overflow" $ do
        it "should wrap around successfully" $
          runCombination ["R60"] `shouldBe` LockState {position = 10, zeroCount = 0}
      describe "Left Overflow" $ do
        it "should wrap around successfully" $
          runCombination ["L70"] `shouldBe` LockState {position = 80, zeroCount = 0}
      describe "Left turn over 100" $ do
        it "should wrap around successfully" $
          runCombination ["L700"] `shouldBe` LockState {position = 50, zeroCount = 0}
      describe "Right turn over 100" $ do
        it "should wrap around successfully" $
          runCombination ["R201"] `shouldBe` LockState {position = 51, zeroCount = 0}

    describe "Count Zeroes" $ do
      it "should count zeroes correctly" $
        runCombination ["L50", "R50", "R50"] `shouldBe` LockState {position = 0, zeroCount = 2}

    describe "Acceptance Test" $ do
      it "should match the example" $
        runCombination
          ["L68", "L30", "R48", "L5", "R60", "L55", "L1", "L99", "R14", "L82"]
          `shouldBe` LockState {position = 32, zeroCount = 3}

    describe "Puzzle Part 1" $ do
      it "should solve Puzzle Part 1" $ do
        fileContents <- readFile "inputs/day01.txt"
        let puzzleInput = lines fileContents
        runCombination puzzleInput `shouldBe` LockState {position = 23, zeroCount = 1152}
