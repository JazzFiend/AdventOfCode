import org.scalatest.funspec.AnyFunSpec

class MinimumCubeCalculatorTest extends AnyFunSpec {
  it("An empty list of games should return a score of 0") {
    assert(MinimumCubeCalculator.calculateMinimumScore(List.empty) == 0)
  }

  it("One game with no hints should return a score of 0") {
    val games = List("Game 3:")
    assert(MinimumCubeCalculator.calculateMinimumScore(games) == 1)
  }

  it("One game with a simple hint should return its only value") {
    val games = List("Game 1: 9 blue")
    assert(MinimumCubeCalculator.calculateMinimumScore(games) == 9)
  }

  it("One game with a full hint should multiply all of the numbers together") {
    val games = List("Game 5: 7 red, 3 blue, 2 green")
    assert(MinimumCubeCalculator.calculateMinimumScore(games) == 42)
  }

  it("When a game has multiple hints, the maximums should be multiplied together") {
    val games = List(
      "Game 1: 1 red, 7 blue, 3 green; 16 blue, 3 red, 1 green; 200 green, 1 blue, 55 red"
    )
    assert(MinimumCubeCalculator.calculateMinimumScore(games) == 16 * 200 * 55)
  }

  it("Missing cubes in hints should be disregarded") {
    val games = List("Game 1: 9 blue; 10 red; 2 green")
    assert(MinimumCubeCalculator.calculateMinimumScore(games) == (9 * 10 * 2))
  }

  it("Multiple games should sum their scores together") {
    val games = List(
      "Game 1: 2 red, 7 blue, 3 green; 10 blue, 3 red, 2 green; 10 green, 2 blue, 10 red",
      "Game 2: 10 red, 7 blue, 3 green; 200 green, 3 blue, 55 red"
    )
    assert(MinimumCubeCalculator.calculateMinimumScore(games) == (10 * 10 * 10) + (55 * 7 * 200))
  }

  it("Acceptance Test") {
    val games = List(
      "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
      "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
      "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
      "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
      "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")
    assert(MinimumCubeCalculator.calculateMinimumScore(games) == 2286)
  }

  it("Puzzle Two") {
    val games = io.Source.fromFile("src/test/scala/input.txt").getLines.toList
    assert(MinimumCubeCalculator.calculateMinimumScore(games) == -1)
  }
}
