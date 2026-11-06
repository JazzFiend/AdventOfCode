module Day02.ProductCollectorSpec (spec) where

import Day02.ProductCollector
import Test.Hspec

spec :: Spec
spec = describe "ProductCollector" $ do
  it "should return nothing if an empty string is given" $ do
    parseProducts "" `shouldBe` []
  it "should return a single product if both numbers are identical" $ do
    parseProducts "1-1" `shouldBe` ["1"]
