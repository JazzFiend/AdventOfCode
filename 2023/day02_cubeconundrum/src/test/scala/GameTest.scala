import org.scalatest.funspec.AnyFunSpec

class GameTest extends AnyFunSpec{
  it("Equals") {
    val game1 = new Game(12, List(new Hint(1, 2, 3)))
    val game2 = new Game(12, List(new Hint(1, 2, 3)))
    assert(game1 == game2)
  }

  describe("Not Equal") {
    val game1 = new Game(8, List(new Hint(8 ,5, 2)))
    it("ID") {
      val game2 = new Game(7, List(new Hint(8, 5, 2)))
      assert(game1 != game2)
    }

    it("Hints") {
      val game2 = new Game(8, List(new Hint(9, 5, 2)))
      assert(game1 != game2)
    }
  }

  it("Hash Code") {
    val game1 = new Game(19, List(new Hint(1, 2, 3)))
    val game2 = new Game(20, List(new Hint(1, 2, 3)))
    assert(game1.hashCode() != game2.hashCode())
  }
}