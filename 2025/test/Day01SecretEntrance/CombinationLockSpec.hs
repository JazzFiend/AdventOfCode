module Day01SecretEntrance.CombinationLockSpec (spec) where

import Control.Exception (evaluate)
import Day01SecretEntrance.CombinationLock
import Test.Hspec

spec :: Spec
spec = describe "CombinationLock" $ do
  describe "runCombinationFinalNumber" $ do
    context "when given an empty input" $ do
      it "the counter should not move" $
        runCombinationFinalNumber [""] `shouldBe` 50

    context "when given a right move" $ do
      it "the counter should move right" $ do
        runCombinationFinalNumber ["R20"] `shouldBe` 70

    context "when given a left move" $ do
      it "the counter should move left" $ do
        runCombinationFinalNumber ["L20"] `shouldBe` 30

    context "when given an invalid move" $ do
      it "throws an error" $ do
        evaluate (runCombinationFinalNumber ["U20"]) `shouldThrow` anyErrorCall

    context "when moving right and overflowing the dial" $ do
      it "should wrap around" $ do
        runCombinationFinalNumber ["R50"] `shouldBe` 0

    context "when moving left and overflowing the dial" $ do
      it "should wrap around" $ do
        runCombinationFinalNumber ["L60"] `shouldBe` 90

    context "when given multiple moves" $ do
      it "should calculate the final number" $ do
        runCombinationFinalNumber ["R20", "L10"] `shouldBe` 60

    context "acceptance tests" $ do
      it "example 1: should calculate the final number" $ do
        runCombinationFinalNumber
          [ "L68",
            "L30",
            "R48",
            "L5",
            "R60",
            "L55",
            "L1",
            "L99",
            "R14",
            "L82"
          ]
          `shouldBe` 32
  describe "runCombinationCountNumber" $ do
    context "when given an empty input and tracking number is not the default" $ do
      it "the final count is 0" $ do
        runCombinationCountNumber [""] 0 `shouldBe` 0
