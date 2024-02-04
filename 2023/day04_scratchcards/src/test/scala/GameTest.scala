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

    it("Games with multiple winning numbers should be scored appropriately") {
      val winningNumbers = List(1, 2, 3, 4, 5)
      val testCases = List(
        (List(1, 12, 13, 14, 15), 1),
        (List(11, 12, 3, 14, 5), 2),
        (List(11, 2, 13, 4, 5), 4),
        (List(1, 2, 3, 14, 5), 8),
        (List(1, 2, 3, 4, 5), 16)
      )
      assert(Game(winningNumbers, testCases(1)._1).score == testCases(1)._2)
    }
  }
}
