object RangeDivider {
  def divide(original: (Long, Long), slices: List[(Long, Long)]): List[(Long, Long)] = {
    if(noValidSlice(original, slices)) { return List(original) }

    val slice = slices.head
    if(slice._1 <= original._1) {
      List(slice, (slice._2 + 1, original._2))
    } else {
      List((original._1, slice._1 - 1), slice)
    }
  }

  private def noValidSlice(original: (Long, Long), slices: List[(Long, Long)]): Boolean = {
    val listOfValidSlices = slices.map(slice => {
      slice._2 >= original._1 && slice._1 <= original._2
    })
    listOfValidSlices.forall(_ == false)
  }
}
