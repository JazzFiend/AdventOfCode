class WordyNumberTranslatorTest extends munit.FunSuite {
  test("An empty string should return itself") {
    val forward = new ForwardWordyNumberTranslator
    val reverse = new ReverseWordyNumberTranslator

    assertEquals(forward.translate(""), "")
    assertEquals(reverse.translate(""), "")
  }

  test("A string with no wordy values should return itself") {
    val forward = new ForwardWordyNumberTranslator
    val reverse = new ReverseWordyNumberTranslator

    assertEquals(forward.translate("fwe5rii3nhj"), "fwe5rii3nhj")
    assertEquals(reverse.translate("fwe5rii3nhj"), "fwe5rii3nhj")
  }

  test("Numbers spelled out should return themselves") {
    val forward = new ForwardWordyNumberTranslator
    val reverse = new ReverseWordyNumberTranslator

    assertEquals(forward.translate("one"), "1")
    assertEquals(reverse.translate("one"), "1")
    assertEquals(forward.translate("two"), "2")
    assertEquals(reverse.translate("two"), "2")
    assertEquals(forward.translate("three"), "3")
    assertEquals(reverse.translate("three"), "3")
    assertEquals(forward.translate("four"), "4")
    assertEquals(reverse.translate("four"), "4")
    assertEquals(forward.translate("five"), "5")
    assertEquals(reverse.translate("five"), "5")
    assertEquals(forward.translate("six"), "6")
    assertEquals(reverse.translate("six"), "6")
    assertEquals(forward.translate("seven"), "7")
    assertEquals(reverse.translate("seven"), "7")
    assertEquals(forward.translate("eight"), "8")
    assertEquals(reverse.translate("eight"), "8")
    assertEquals(forward.translate("nine"), "9")
    assertEquals(reverse.translate("nine"), "9")
  }

  test("A number should be translated when there are other characters there too.") {
    val forward = new ForwardWordyNumberTranslator
    val reverse = new ReverseWordyNumberTranslator

    assertEquals(forward.translate("sg3onegerr4e"), "sg31gerr4e")
    assertEquals(reverse.translate("sg3onegerr4e"), "sg31gerr4e")
    assertEquals(forward.translate("1nffrfourvgrf6ed"), "1nffr4vgrf6ed")
    assertEquals(reverse.translate("1nffrfourvgrf6ed"), "1nffr4vgrf6ed")
    assertEquals(forward.translate("f3asevengrae7r"), "f3a7grae7r")
    assertEquals(reverse.translate("f3asevengrae7r"), "f3a7grae7r")
  }

  test("Multiple numbers in a string should translate correctly") {
    val forward = new ForwardWordyNumberTranslator
    val reverse = new ReverseWordyNumberTranslator

    assertEquals(forward.translate("threefour"), "3four")
    assertEquals(reverse.translate("threefour"), "three4")
    assertEquals(forward.translate("twone"), "2ne")
    assertEquals(reverse.translate("twone"), "tw1")
  }
}
