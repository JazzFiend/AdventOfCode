class Hint(val red:Int, val green:Int, val blue:Int) {
  override def equals(that: Any): Boolean = {
    that match {
      case that: Hint =>
        that.isInstanceOf[Hint] &&
          this.red == that.red &&
          this.green == that.green &&
          this.blue == that.blue
      case _ => false
    }
  }

  override def hashCode: Int = {
    val prime = 31
    prime * red * green * blue
  }
}
