class ReverseWordyNumberTranslator extends WordyNumberTranslator {
  override def translate(s: String): String = {
    translateRecursive(s.reverse).reverse
  }

  override def translateIfValid(s: String): String = {
    if (s.length == 3) {
      s match {
        case "eno" => "1"
        case "owt" => "2"
        case "xis" => "6"
        case default => s
      }
    } else if (s.length == 4) {
      s match {
        case "ruof" => "4"
        case "evif" => "5"
        case "enin" => "9"
        case default => s
      }
    } else if (s.length == 5) {
      s match {
        case "eerht" => "3"
        case "neves" => "7"
        case "thgie" => "8"
        case default => s
      }
    } else {
      throw new IllegalArgumentException("Cannot translate string of length " + s.length)
    }
  }
}
