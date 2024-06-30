import org.scalatest.funspec.AnyFunSpec

class RangedAlmanacMapTest extends AnyFunSpec {
  it("Constructor Test") {
    RangedAlmanacMap("source", "destination", List(new MapRange(10, 20, 5)))
  }


}
