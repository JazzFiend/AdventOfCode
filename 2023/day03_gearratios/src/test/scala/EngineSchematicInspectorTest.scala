import org.scalatest.funspec.AnyFunSpec

// Maybe I could play with import org.scalatest.matchers.should.Matchers._ to improve readability...
class EngineSchematicInspectorTest extends AnyFunSpec {
  describe("Zero sum tests") {
    it("An empty schematic should have a part sum of zero") {
      assert(EngineSchematicInspector.calculatePartSum(List.empty) == 0)
    }

    it("All empty spaces should return zero") {
      val schematic = List("...")
      assert(EngineSchematicInspector.calculatePartSum(schematic) == 0)
    }

    it("Just one non-part number should return 0") {
      val schematic = List("3467")
      assert(EngineSchematicInspector.calculatePartSum(schematic) == 0)
    }

    it("One symbol with no number should return a part of zero") {
      val schematic = List(".%.")
      assert(EngineSchematicInspector.calculatePartSum(schematic) == 0)
    }
  }

  describe("Surrounding symbol tests") {
    // Can we replace these with a Parameterized Test?
    it("One one-digit part number to the right of the symbol") {
      val schematic = List("+8")
      assert(EngineSchematicInspector.calculatePartSum(schematic) == 8)
    }

    it("One many-digit part number to the right of the symbol") {
      val schematic = List("/456")
      assert(EngineSchematicInspector.calculatePartSum(schematic) == 456)
    }

    it("One one-digit part number to the left of the symbol") {
      val schematic = List("3$")
      assert(EngineSchematicInspector.calculatePartSum(schematic) == 3)
    }

    it("One many-digit part number to the left of the symbol") {
      val schematic = List("357$")
      assert(EngineSchematicInspector.calculatePartSum(schematic) == 357)
    }

    it("One digit directly below a symbol should be summed") {
      val schematic = List(".4.", ".&.")
      assert(EngineSchematicInspector.calculatePartSum(schematic) == 4)
    }

    it("One digit directly above a symbol should be summed") {
      val schematic = List("..@", "..9")
      assert(EngineSchematicInspector.calculatePartSum(schematic) == 9)
    }

    it("Upper Left Symbol") {
      val schematic = List("!..", ".5.", "...")
      assert(EngineSchematicInspector.calculatePartSum(schematic) == 5)
    }

    it("Upper Right Symbol") {
      val schematic = List("..+", ".2.", "...")
      assert(EngineSchematicInspector.calculatePartSum(schematic) == 2)
    }

    it("Lower Left Symbol") {
      val schematic = List("...", ".3.", "-..")
      assert(EngineSchematicInspector.calculatePartSum(schematic) == 3)
    }

    it("Lower Right Symbol") {
      val schematic = List("...", ".4.", "..*")
      assert(EngineSchematicInspector.calculatePartSum(schematic) == 4)
    }

    it("Long number with a symbol on bottom") {
      val schematic = List("........",
                           ".445784.",
                           "....*...")
      assert(EngineSchematicInspector.calculatePartSum(schematic) == 445784)
    }

    it("Long number with a symbol on top") {
      val schematic = List("....#..",
                           ".45156.",
                           ".......")
      assert(EngineSchematicInspector.calculatePartSum(schematic) == 45156)
    }
  }

  describe("Ignore empty entries") {
    it("Empty entries after a number should be ignored") {
      val schematic = List("...#94.")
      assert(EngineSchematicInspector.calculatePartSum(schematic) == 94)
    }

    it("Empty entries before a number should be ignored") {
      val schematic = List("...238%...")
      assert(EngineSchematicInspector.calculatePartSum(schematic) == 238)
    }
  }

  it("A symbol with a number on either side should sum the two") {
    val schematic = List("3^87")
    assert(EngineSchematicInspector.calculatePartSum(schematic) == 90)
  }

  it("A number with multiple symbols touching it should only be summed once") {
    val schematic = List("......$....",
                         "..#1234...",
                         "...^...!..")
    assert(EngineSchematicInspector.calculatePartSum(schematic) == 1234)
  }

  it("Two entries on a line should be summed") {
    val schematic = List(".$10.8.100).")
    assert(EngineSchematicInspector.calculatePartSum(schematic) == 110)
  }

  it("Two lines with no numbers should be zero") {
    val schematic = List("....", "....")
    assert(EngineSchematicInspector.calculatePartSum(schematic) == 0)
  }

  it("Two lines with no symbols should be zero") {
    val schematic = List(".22.", ".975")
    assert(EngineSchematicInspector.calculatePartSum(schematic) == 0)
  }

  it("Identical numbers should not be dropped") {
    val schematic = List("1-1-1-1-1-1-1")
    assert(EngineSchematicInspector.calculatePartSum(schematic) == 7)
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

    assert(EngineSchematicInspector.calculatePartSum(schematic) == 4361)
  }

  it("Acceptance Test 2") {
    val schematic = List("12.......*..",
                         "+.........34",
                         ".......-12..",
                         "..78........",
                         "..*....60...",
                         "78..........",
                         ".......23...",
                         "....90*12...",
                         "............",
                         "2.2......12.",
                         ".*.........*",
                         "1.1.......56")
    assert(EngineSchematicInspector.calculatePartSum(schematic) == 413)
  }

  it("Puzzle One") {
    val schematic = io.Source.fromFile("src/test/scala/input.txt").getLines.toList
    assert(EngineSchematicInspector.calculatePartSum(schematic) == 539713)
  }
}
