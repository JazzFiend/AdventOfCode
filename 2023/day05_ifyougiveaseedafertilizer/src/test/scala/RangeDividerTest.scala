import org.scalatest.funspec.AnyFunSpec
// I need a function that will take a big range and divide it into smaller ranges,
// based on a list of smaller ranges.

//Example:
// Big Renge: (0, 11)
// Small Ranges: (2, 4), (6, 7), (9, 9)
// Result: (0, 1), (2, 4), (5, 5), (6, 7), (8, 8), (9, 9), (10, 11)

class RangeDividerTest extends AnyFunSpec {
  it("No slices") {
    val original = (0L, 10L)
    assert(RangeDivider.divide(original, List.empty) == List(original))
  }
}
