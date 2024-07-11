import scala.annotation.tailrec

object RangeDivider {
  def divide(original: (Long, Long), slices: List[(Long, Long)]): List[(Long, Long)] = {
    recursiveSlice(List(original), slices, original)
  }

  @tailrec
  private def recursiveSlice(largeRanges: List[(Long, Long)], slices: List[(Long, Long)], original: (Long, Long)): List[(Long, Long)] = {
    val sliced = sliceRanges(largeRanges, slices, original)
    if (sliced == largeRanges) {
      sliced
    } else {
      recursiveSlice(sliced, slices, original)
    }
  }

  private def sliceRanges(largeRanges: List[(Long, Long)], slices: List[(Long, Long)], original: (Long, Long)): List[(Long, Long)] = {
    largeRanges.flatMap(largeRange => {
      if (noValidSlice(largeRange, slices) && isWithinOriginalRange(original, largeRange)) {
        List(largeRange)
      } else {
        slices
          .filter(slice => isSliceValid(largeRange, slice))
          .flatMap(slice => {
            // This is looking awfully similar to the computeMap function in MapRange. Either these can be
            // consolidated or one can be made simpler.
            determineSlices(largeRange, slice)
          })
      }
    }).distinct
  }

  private def determineSlices(largeRange: (Long, Long), slice: (Long, Long)): List[(Long, Long)] = {
    if (largeRange == slice) {
      List(largeRange)
    } else if (slice._1 > largeRange._1 && slice._2 < largeRange._2) {
      List((largeRange._1, slice._1 - 1), slice, (slice._2 + 1, largeRange._2))
    } else if (slice._1 == largeRange._1) {
      List(slice, (slice._2 + 1, largeRange._2))
    } else {
      List((largeRange._1, slice._1 - 1), slice)
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

  private def isWithinOriginalRange(original: (Long, Long), small: (Long, Long)): Boolean = {
    original._1 <= small._1 && original._2 >= small._2
  }
}
