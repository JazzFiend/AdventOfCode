import org.scalatest.funspec.AnyFunSpec

class AlmanacParserTest extends AnyFunSpec{
  it("An empty almanac should give an empty list of entries") {
    assert(AlmanacParser.parse(List.empty) == List.empty)
  }

  it("An almanac with just seeds should result in an empty list") {
    val justSeeds = List("seeds: 1 4 6 7")
    assert(AlmanacParser.parse(justSeeds) == List.empty)
  }
}
