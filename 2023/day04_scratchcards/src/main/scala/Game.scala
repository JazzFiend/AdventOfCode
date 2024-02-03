class Game(val winningNumbers: List[Int], val playerNumbers: List[Int]) {
  override def equals(that: Any): Boolean = {
    that match {
      case that: Game =>
        that.isInstanceOf[Game] &&
          that.winningNumbers.equals(winningNumbers) &&
          that.playerNumbers.equals(playerNumbers)
      case _ => false
    }
  }
}
