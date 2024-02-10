import scala.math.pow

class Game(val cardNumber: Int, val winningNumbers: List[Int], val playerNumbers: List[Int]) {
  val score: Int = calculateScore()
  val wins: Int = countWins()

  override def equals(that: Any): Boolean = {
    that match {
      case that: Game =>
        that.isInstanceOf[Game] &&
          that.cardNumber.equals(cardNumber) &&
          that.winningNumbers.equals(winningNumbers) &&
          that.playerNumbers.equals(playerNumbers)
      case _ => false
    }
  }

  private def calculateScore(): Int = {
    val winningNumbers = calculateWinningNumbers()
    pow(2, winningNumbers.length - 1).toInt
  }

  private def countWins(): Int = {
    calculateWinningNumbers().length
  }

  private def calculateWinningNumbers() = {
    playerNumbers.filter(number => winningNumbers.contains(number))
  }
}
