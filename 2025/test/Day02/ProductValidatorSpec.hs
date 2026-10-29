module Day02.ProductValidatorSpec (spec) where

import Day02.ProductValidator
import Test.Hspec

-- I screwed this up. We should be invalidating ids that ONLY have repeating numbers. Examples:
-- 11
-- 22.
-- 99.
-- 1010.
-- 1188511885.
-- 222222.
-- 446446.
-- 38593859.

spec :: Spec
spec = describe "Product Validator" $ do
  it "should return false for an empty ID" $
    isValidProduct "" `shouldBe` False
  it "should return true for a single digit product id" $
    isValidProduct "5" `shouldBe` True
  it "should return true for two different digits" $
    isValidProduct "46" `shouldBe` True
  it "should return false for duplicated digits" $
    isValidProduct "88" `shouldBe` False
  it "should return true for duplicated digits later in the number" $
    isValidProduct "3599" `shouldBe` True

  it "should return false when duplicating two numbers" $
    isValidProduct "6767" `shouldBe` False