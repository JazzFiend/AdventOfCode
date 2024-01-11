import org.scalatest.funspec.AnyFunSpec

class GearInspectorTest extends AnyFunSpec {
  describe("Zero Sum Tests") {
    it("Empty schematic should return 0") {
      val schematic = List.empty
      assert(GearInspector.calculateGearRatioSum(schematic) == 0)
    }

    it("One line of no part numbers should return 0") {
      val schematic = List("....")
      assert(GearInspector.calculateGearRatioSum(schematic) == 0)
    }

    it("Several lines of no part numbers should return 0") {
      val schematic = List("....", "....", "....")
      assert(GearInspector.calculateGearRatioSum(schematic) == 0)
    }


  }
}
