module Day02.ProductRangeValidatorSpec (spec) where

import Day02.ProductRangeValidator
import Test.Hspec

spec :: Spec
spec = describe "ProductRangeValidator" $ do
  describe "Acceptance Test" $ do
    it "should match the example" $
      validProductIdSum
        "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124"
        `shouldBe` 1227775554