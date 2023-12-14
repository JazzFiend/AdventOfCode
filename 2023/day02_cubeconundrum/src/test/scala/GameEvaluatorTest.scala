import org.scalatest.funsuite.AnyFunSuite

class GameEvaluatorTest extends AnyFunSuite {
  test("An empty list of games should return a score of 0.") {
    assert(GameEvaluator.scorePossibleGames(List.empty, 4, 67, 22) == 0)
  }

  test("One impossible game with one hint should return a score of 0") {
    val games = List("Game 1: 3 blue, 4 red, 2 green")
    assert(GameEvaluator.scorePossibleGames(games, 0, 0, 0) == 0)
  }

  test("One possible game with one hint should return the game ID") {
    val games = List("Game 14: 3 blue, 4 red, 2 green")
    assert(GameEvaluator.scorePossibleGames(games, 5, 5, 5) == 14)
  }

  test("A game with identical cubes in the hint should still be possible") {
    val games = List("Game 22: 6 red, 14 blue, 9 green")
    assert(GameEvaluator.scorePossibleGames(games, 6, 9, 14) == 22)
  }

  test("Two possible games should return the sum of the ids") {
    val games = List(
      "Game 4: 11 blue, 6 green",
      "Game 11: 10 red"
    )
    assert(GameEvaluator.scorePossibleGames(games, 11, 6, 12) == 15)
  }

  test("All hints should be evaluated when checking if a game is possible") {
    val games = List(
      "Game 33: 1 blue, 10 red, 6 green; 10 green, 3 red",
      "Game 9: 1 blue; 150 red, 10 green"
    )
    assert(GameEvaluator.scorePossibleGames(games, 20, 20, 20) == 33)
  }

  test("Acceptance Test") {
    val games = List(
      "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
      "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
      "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
      "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
      "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")
    assert(GameEvaluator.scorePossibleGames(games, 12, 13, 14) == 8)
  }

  test("Puzzle One") {
    val games = io.Source.fromFile("src/test/scala/input.txt").getLines.toList
    assert(GameEvaluator.scorePossibleGames(games, 12, 13, 14) == 2164)
  }
}
