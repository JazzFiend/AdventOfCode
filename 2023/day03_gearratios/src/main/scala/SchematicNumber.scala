class SchematicNumber(val value: Int, val coordinates: Set[(Int, Int)]) {
  override def equals(that: Any): Boolean = {
    that match {
      case that: SchematicNumber =>
        that.isInstanceOf[SchematicNumber] &&
        that.value == value &&
        that.coordinates.equals(coordinates)
      case _ => false
    }
  }

  override def hashCode(): Int = {
    359 * value * coordinates.hashCode()
  }
}
