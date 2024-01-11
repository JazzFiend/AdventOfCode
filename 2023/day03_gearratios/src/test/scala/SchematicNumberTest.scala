import org.scalatest.funspec.AnyFunSpec

class SchematicNumberTest extends AnyFunSpec {
  it("Equal Case") {
    val a = SchematicNumber(546, Set((3, 4), (4, 4), (5, 4)))
    val b = SchematicNumber(546, Set((3, 4), (4, 4), (5, 4)))
    assert(a == b)
    assert(a.hashCode() == b.hashCode())
  }

  describe("Non Equal cases") {
    it("Values are different") {
      val a = SchematicNumber(9876, Set((11, 2), (12, 2), (13, 2)))
      val b = SchematicNumber(3245, Set((11, 2), (12, 2), (13, 2)))
      assert(a != b)
      assert(a.hashCode() != b.hashCode())
    }

    it("Coordinate size not equal") {
      val a = SchematicNumber(9876, Set((11, 2), (12, 2), (13, 2)))
      val b = SchematicNumber(9876, Set((11, 2), (12, 2), (13, 2), (14, 2)))
      assert(a != b)
      assert(a.hashCode() != b.hashCode())
    }

    it("Coordinates not equal") {
      val a = SchematicNumber(9876, Set((11, 2), (12, 2), (13, 2)))
      val b = SchematicNumber(9876, Set((10, 2), (12, 2), (11, 2)))
      assert(a != b)
      assert(a.hashCode() != b.hashCode())
    }
  }
}
