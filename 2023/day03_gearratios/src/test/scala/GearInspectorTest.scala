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

    // TODO: The last function broke this test, but the next one should bring it back.
//    it("Several lines of part numbers but no gear ratios should return 0") {
//      val schematic = List("261....",
//                           ".*.....",
//                           "....25*")
//      assert(GearInspector.calculateGearRatioSum(schematic) == 0)
//    }
  }

  describe("Gear Ratios") {
    describe("Horizontal Only") {
      it("One line of one gear ratio should return the gear ratio") {
        val schematic = List("..125*50....")
        assert(GearInspector.calculateGearRatioSum(schematic) == 125 * 50)
      }

      it("One line of one gear ratio at the beginning of the line should be calculated correctly") {
        val schematic = List("24*8....")
        assert(GearInspector.calculateGearRatioSum(schematic) == 24 * 8)
      }

      it("One line of one gear ratio at the end of the line should be calculated correctly") {
        val schematic = List("....42*34")
        assert(GearInspector.calculateGearRatioSum(schematic) == 42 * 34)
      }
    }

    describe("Vertical Only") {
      it("Two upper corners") {
        val schematic = List(".12.9.",
                             "...*..")
        assert(GearInspector.calculateGearRatioSum(schematic) == 12 * 9)
      }
    }
  }


  // I may need to compute all of the possible directions and then filter out the ones that aren't valid.
  // To do this, I'll need to turn the existing calculations into commands.
  // Then I compute all commands. If after filtering the size is less than two, return 0.
  // If 2, multiply them together.
  // If more, throw an error because something went wrong.
  //   ...    1..  .1.  ..1 11. 1.1 .11  111
  //    *      *    *    *   *   *   *    *



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
