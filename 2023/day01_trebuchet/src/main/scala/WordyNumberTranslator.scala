trait WordyNumberTranslator {
  def translate(s: String): String

  def translateRecursive(s: String): String = {
    if (s.length >= 5 && containsWordyNumber(s.substring(0, 5))) {
      translateIfValid(s.substring(0, 5)) + s.substring(5)
    } else {
      if (s.length >= 4 && containsWordyNumber(s.substring(0, 4))) {
        translateIfValid(s.substring(0, 4)) + s.substring(4)
      } else {
        if (s.length < 3) {
          return s
        }

        if (containsWordyNumber(s.substring(0, 3))) {
          translateIfValid(s.substring(0, 3)) + s.substring(3)
        } else {
          s.charAt(0) + translateRecursive(s.substring(1))
        }
      }
    }
  }

  private def containsWordyNumber(firstN: String): Boolean = {
    !containsDigit(firstN) && containsDigit(translateIfValid(firstN))
  }

  private def containsDigit(firstThree: String): Boolean = {
    firstThree.matches("\\d")
  }

  protected def translateIfValid(s: String): String
}
