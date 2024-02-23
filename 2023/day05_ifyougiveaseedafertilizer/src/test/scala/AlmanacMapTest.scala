import org.scalatest.funspec.AnyFunSpec

class AlmanacMapTest extends AnyFunSpec {
  describe("Equals") {
    it("Two identical AlmanacMaps should be equal") {
      val one = AlmanacMap("source", "destination", List(MapRange(3, 2, 1)))
      val two = AlmanacMap("source", "destination", List(MapRange(3, 2, 1)))
      assert(one == two)
    }
  }

  describe("Not equals") {
    it("Different source") {
      val one = AlmanacMap("source", "destination", List(MapRange(3, 2, 1)))
      val two = AlmanacMap("different", "destination", List(MapRange(3, 2, 1)))
      assert(one != two)
    }

    it("Different destination") {
      val one = AlmanacMap("source", "destination", List(MapRange(3, 2, 1)))
      val two = AlmanacMap("source", "different", List(MapRange(3, 2, 1)))
      assert(one != two)
    }

    it("Different mapRanges") {
      val one = AlmanacMap("source", "destination", List(MapRange(3, 2, 1)))
      val two = AlmanacMap("source", "destination", List.empty)
      assert(one != two)
    }
  }
}
