import org.scalatest.funsuite.AnyFunSuite

class GameTranslatorTest extends AnyFunSuite {
  test("A badly formatted game should throw") {
    assertThrows[IllegalArgumentException] {
      GameTranslator.translateGames(List(""))
    }
  }

  test("A game with no hints should be created successfully") {
    val gameList = List("Game 4:")
    assert(GameTranslator.translateGames(gameList) == List(new Game(4, List.empty)))
  }

  test("A game with simple hint should be created successfully") {
    val gameList = List("Game 12: 8 green")
    val hints = List(new Hint(0, 8, 0))
    val expected = List(new Game(12, hints))
    assert(GameTranslator.translateGames(gameList) == expected)
  }

  test("A game with a complicated hint should be created successfully") {
    val gameList = List("Game 9: 10 blue, 4 red, 1 green")
    val hints = List(new Hint(4, 1, 10))
    val expected = List(new Game(9, hints))
    assert(GameTranslator.translateGames(gameList).equals(expected))
  }

  test("A game with several hints should be created successfully") {
    val gameList = List("Game 22: 1 blue, 9 red, 77 green; 3 green, 10 red")
    val hints = List(new Hint(9, 77, 1), new Hint(10, 3, 0))
    val expected = List(new Game(22, hints))
    assert(GameTranslator.translateGames(gameList).equals(expected))
  }
}