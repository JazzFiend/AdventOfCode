module Day02.ProductCollectorSpec (spec) where

import Day02.ProductCollector
import Test.Hspec

spec :: Spec
spec = describe "ProductCollector" $ do
  it "should return nothing if an empty string is given" $ do
    parseProducts "" `shouldBe` []
  it "should return a single product if both numbers are identical" $ do
    parseProducts "1-1" `shouldBe` ["1"]
  it "should return two products when the numbers are sequential" $ do
    parseProducts "5-6" `shouldBe` ["5", "6"]
  it "should return all products between two numbers" $ do
    parseProducts "3-9" `shouldBe` ["3", "4", "5", "6", "7", "8", "9"]
  it "acceptance test" $ do
    parseProducts "498522-498528" `shouldBe` ["498522", "498523", "498524", "498525", "498526", "498527", "498528"]
