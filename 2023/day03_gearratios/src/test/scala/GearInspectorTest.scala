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

    it("One line of only part numbers should return 0") {
      val schematic = List(".48...8226.")
      assert(GearInspector.calculateGearRatioSum(schematic) == 0)
    }

    it("Several lines of only part numbers should return 0") {
      val schematic = List("..234..", "...5676", "..2.2..")
      assert(GearInspector.calculateGearRatioSum(schematic) == 0)
    }

    it("One line of part numbers but no gears should return 0") {
      val schematic = List("354&...31..5565-")
      assert(GearInspector.calculateGearRatioSum(schematic) == 0)
    }

    it("Several lines of part numbers but no gears should return 0") {
      val schematic = List("..45..2",
                           "..-...)",
                           "752....")
      assert(GearInspector.calculateGearRatioSum(schematic) == 0)
    }

    it("One line of part numbers but no gear ratios should return 0") {
      val schematic = List("435*...*...59904")
      assert(GearInspector.calculateGearRatioSum(schematic) == 0)
    }

    it("Several lines of part numbers but no gear ratios should return 0") {
      val schematic = List("261....",
                           ".*.....",
                           "....25*")
      assert(GearInspector.calculateGearRatioSum(schematic) == 0)
    }
  }

  it("Acceptance Test") {
    val schematic = List("467..114..",
                         "...*......",
                         "..35..633.",
                         "......#...",
                         "617*......",
                         ".....+.58.",
                         "..592.....",
                         "......755.",
                         "...$.*....",
                         ".664.598..")
    assert(GearInspector.calculateGearRatioSum(schematic) == 467835)
  }
}
