object RangeDivider {
  def divide(original: (Long, Long), slices: List[(Long, Long)]): List[(Long, Long)] = {
    if(noValidSlice(original, slices)) { return List(original) }

    val slice = slices.filter(slice => isSliceValid(original, slice)).head
    // This is looking awfully similar to the computeMap function in MapRange. Either these can be
    // consolidated or one can be made simpler.
    if(original == slice) {
      List(original)
    } else if(slice._1 > original._1 && slice._2 < original._2) {
      List((original._1, slice._1 - 1), slice, (slice._2 + 1, original._2))
    } else if(slice._1 == original._1) {
      List(slice, (slice._2 + 1, original._2))
    } else {
      List((original._1, slice._1 - 1), slice)
    }
  }

  private def noValidSlice(original: (Long, Long), slices: List[(Long, Long)]): Boolean = {
    slices
      .map(slice => isSliceValid(original, slice))
      .forall(_ == false)
  }

  private def isSliceValid(original: (Long, Long), slice: (Long, Long)) = {
    slice._2 >= original._1 && slice._1 <= original._2
  }
}
