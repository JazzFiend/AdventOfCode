object RangeDivider {
  def divide(original: (Long, Long), slices: List[(Long, Long)]): List[(Long, Long)] = {
    if(noValidSlice(original, slices)) { return List(original) }

    val slice = slices.head

    List((slice._1, slice._2), (original._1 + 1, original._2))
  }

  private def noValidSlice(original: (Long, Long), slices: List[(Long, Long)]): Boolean = {
    val listOfValidSlices = slices.map(slice => {
      slice._2 >= original._1 && slice._1 <= original._2
    })
    listOfValidSlices.forall(_ == false)
  }
}
