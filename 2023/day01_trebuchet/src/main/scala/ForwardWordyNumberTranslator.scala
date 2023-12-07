class ForwardWordyNumberTranslator extends WordyNumberTranslator {
  override def translate(s: String): String = {
    translateRecursive(s)
  }

  override def translateIfValid(s: String): String = {
    if (s.length == 3) {
      s match {
        case "one" => "1"
        case "two" => "2"
        case "six" => "6"
        case default => s
      }
    } else if (s.length == 4) {
      s match {
        case "four" => "4"
        case "five" => "5"
        case "nine" => "9"
        case default => s
      }
    } else if (s.length == 5) {
      s match {
        case "three" => "3"
        case "seven" => "7"
        case "eight" => "8"
        case default => s
      }
    } else {
      throw new IllegalArgumentException("Cannot translate string of length " + s.length)
    }
  }
}
