import org.scalatest.funspec.AnyFunSpec

class HintTest extends AnyFunSpec {
  describe("Equals") {
    it("Equal Hints") {
      val hint1 = new Hint(4, 7, 18)
      val hint2 = new Hint(4, 7, 18)
      assert(hint1 == hint2)
    }

    describe("Not Equals tests") {
      val hint = new Hint(6, 3, 121)
      it("Blue") {
        val badBlue = new Hint(6, 3, 120)
        assert(hint != badBlue)
      }

      it("Green") {
        val badGreen = new Hint(6, 2, 121)
        assert(hint != badGreen)
      }

      it("Red") {
        val badRed = new Hint(7, 3, 121)
        assert(hint != badRed)
      }
    }
  }

  it("hashCode") {
    val hint1 = new Hint(4, 7, 18)
    val hint2 = new Hint(4, 7, 17)
    assert(hint1.hashCode() != hint2.hashCode())
  }
}