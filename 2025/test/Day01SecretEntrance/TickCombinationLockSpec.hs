module Day01SecretEntrance.TickCombinationLockSpec (spec) where

import Day01SecretEntrance.TickCombinationLock (countAllTicks)
import Test.Hspec

spec :: Spec
spec = describe "TickCombinationLock" $ do
  describe "countAllTicks" $ do
    context "when given an empty input and the tracking number is not the default" $ do
      it "the final count is zero" $ do
        countAllTicks [] 0 `shouldBe` 0

    context "when given an empty input and tracking number is the default" $ do
      it "the final count is 1" $ do
        countAllTicks [] 50 `shouldBe` 1

    context "when we never pass the tracking number" $ do
      it "the final count is 0" $ do
        countAllTicks ["R10", "L20", "R30", "R20"] 0 `shouldBe` 0

    context "when we hit the tracking number" $ do
      it "the final count should be equal to the number of times we land on the tracking number (but never pass it)" $ do
        countAllTicks ["R50", "R50", "R50", "L100"] 0 `shouldBe` 3
