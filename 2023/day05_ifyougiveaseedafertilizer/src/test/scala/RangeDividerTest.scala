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

  it("Slices are out of range compared to original") {
    val original = (10L, 20L)
    val slices = List((0L, 9L), (21L, 30L))
    assert(RangeDivider.divide(original, slices) == List(original))
  }

  it("Slice just the smallest number") {
    val original = (10L, 20L)
    val slices = List((10L, 10L))
    val expected = List((10L, 10L), (11L, 20L))
    assert(RangeDivider.divide(original, slices) == expected)
  }

  it("Slice several small numbers") {
    val original = (10L, 20L)
    val slices = List((10L, 12L))
    val expected = List((10L, 12L), (13L, 20L))
    assert(RangeDivider.divide(original, slices) == expected)
  }

  it("Slice several large numbers") {
    val original = (10L, 20L)
    val slices = List((17L, 20L))
    val expected = List((10L, 16L), (17L, 20L))
    assert(RangeDivider.divide(original, slices) == expected)
  }

  it("Slice just largest number") {
    val original = (10L, 20L)
    val slices = List((20L, 20L))
    val expected = List((10L, 19L), (20L, 20L))
    assert(RangeDivider.divide(original, slices) == expected)
  }
}
