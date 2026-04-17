module Day01SecretEntrance.CombinationLockSpec (spec) where

import Control.Exception (evaluate)
import Day01SecretEntrance.CombinationLock
import Test.Hspec

spec :: Spec
spec = describe "CombinationLock" $ do
  context "when given an empty input" $ do
    it "the counter should not move" $
      runCombinationFinalNumber "" `shouldBe` 50

  context "when given a right move" $ do
    it "the counter should move right" $ do
      runCombinationFinalNumber "R20" `shouldBe` 70

  context "when given a left move" $ do
    it "the counter should move left" $ do
      runCombinationFinalNumber "L20" `shouldBe` 30

  context "when given an invalid move" $ do
    it "throws an error" $ do
      evaluate (runCombinationFinalNumber "U20") `shouldThrow` anyErrorCall

  context "when moving right and overflowing the dial" $ do
    it "should wrap around" $ do
      runCombinationFinalNumber "R50" `shouldBe` 0

  context "when moving left and overflowing the dial" $ do
    it "should wrap around" $ do
      runCombinationFinalNumber "L60" `shouldBe` 90
