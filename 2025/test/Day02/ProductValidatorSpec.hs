module Day02.ProductValidatorSpec (spec) where

import Day02.ProductValidator
import Test.Hspec

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
  it "should return false for duplicated digits later in the number" $
    isValidProduct "3599" `shouldBe` False