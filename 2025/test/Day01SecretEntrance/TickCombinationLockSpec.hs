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
