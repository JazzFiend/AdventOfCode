module Day01Spec (spec) where

import Test.Hspec
import Day01

-- Use the small example input AoC provides for each puzzle.
exampleInput :: String
exampleInput = "1\n2\n3"

spec :: Spec
spec = describe "Day01" $ do
  describe "part1" $ do
    it "solves the example" $
      part1 (parse exampleInput) `shouldBe` 0  -- replace 0 with expected answer

  describe "part2" $ do
    it "solves the example" $
      part2 (parse exampleInput) `shouldBe` 0  -- replace 0 with expected answer