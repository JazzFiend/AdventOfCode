class Game(val id: Int, val hints: List[Hint]) {
  override def equals(that: Any): Boolean = {
    that match {
      case that: Game =>
        that.isInstanceOf[Game] &&
          this.id == that.id &&
          this.hints == that.hints
      case _ => false
    }
  }

  override def hashCode: Int = {
    val prime = 31
    prime * id * hints.hashCode()
  }
}
