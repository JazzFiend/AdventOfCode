import org.scalatest.funspec.AnyFunSpec

class GameTest extends AnyFunSpec{
  describe("Equals") {
    it("Incorrect winning numbers") {
      val a = Game(List(3, 6, 8), List(9, 1, 2))
      val b = Game(List(3, 9, 8), List(9, 1, 2))
      assert(!a.equals(b))
    }

    it("Incorrect player numbers") {
      val a = Game(List(3, 6, 8), List(9, 1, 2))
      val b = Game(List(3, 6, 8), List(4, 1, 2))
      assert(!a.equals(b))
    }

    it("Equal games") {
      val a = Game(List(3, 6, 8), List(9, 1, 2))
      val b = Game(List(3, 6, 8), List(9, 1, 2))
      assert(a.equals(b))
    }
  }

  describe("Scoring") {
    it("An empty game should have a score of zero") {
      assert(Game(List.empty, List.empty).score == 0)
    }

    it("A game with no winning numbers should have a score of zero") {
      assert(Game(List(23, 56, 8), List(45, 111, 2)).score == 0)
    }

    it("A game with one winning number should have a score of 1") {
      assert(Game(List(34, 9, 11), List(8, 653, 9)).score == 1)
    }
  }
}
