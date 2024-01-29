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

      it("Two bottom corners") {
        val schematic = List(".*....",
                             "4.98..")
        assert(GearInspector.calculateGearRatioSum(schematic) == 4 * 98)
      }
    }

    describe("Combine Horizontal and Vertical") {
      it("One on top and one to left") {
        val schematic = List(".495..",
                             "7*...")
        assert(GearInspector.calculateGearRatioSum(schematic) == 495 * 7)
      }

      it("One on bottom and one to right") {
        val schematic = List("..*12",
                             "..32.")
        assert(GearInspector.calculateGearRatioSum(schematic) == 32 * 12)
      }
    }

    describe("Error Cases") {
      it("Three values around a gear should throw an exception") {
        val schematic = List(".487..",
                             ".2*65")
        val thrown = intercept[RuntimeException] {
          GearInspector.calculateGearRatioSum(schematic)
        }
        assert(thrown.getMessage == "Too many values around the gear")
      }
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

  it("Puzzle Two") {
    val schematic = io.Source.fromFile("src/test/scala/input.txt").getLines.toList
    assert(GearInspector.calculateGearRatioSum(schematic) == 84159075)
  }
}
