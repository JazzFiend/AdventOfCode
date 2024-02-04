import scala.math.pow

class Game(val winningNumbers: List[Int], val playerNumbers: List[Int]) {
  val score: Int = calculateScore()

  override def equals(that: Any): Boolean = {
    that match {
      case that: Game =>
        that.isInstanceOf[Game] &&
          that.winningNumbers.equals(winningNumbers) &&
          that.playerNumbers.equals(playerNumbers)
      case _ => false
    }
  }

  private def calculateScore(): Int = {
    val matchingItems = playerNumbers.filter(number => winningNumbers.contains(number))
    pow(2, matchingItems.length - 1).toInt
  }
}
