object TrebuchetCalibrator {
  def calculateCalibration(calibrations : List[String]): Int = {
    calibrations.map(calibration => calculateSingleCalibration(calibration)).sum
  }

  def calculateCalibrationWordy(calibrations:List[String]): Int = {
    calibrations.map(calibration => calculateSingleWordyCalibration(calibration)).sum
  }

  private def calculateSingleCalibration(calibration: String) = {
    val numbersOnly: String = calibration.filter(c => c.isDigit)
    val twoDigits: String = numbersOnly.charAt(0).toString + numbersOnly.last.toString
    twoDigits.toInt
  }

  private def calculateSingleWordyCalibration(calibration: String) = {
    val forward = new ForwardWordyNumberTranslator
    val reverse = new ReverseWordyNumberTranslator
    val firstNumberTranslated = forward.translate(calibration)
    val lastNumberTranslated = reverse.translate(calibration)
    val firstTranslationNumbersOnly = firstNumberTranslated.filter(c => c.isDigit)
    val lastTranslationNumbersOnly = lastNumberTranslated.filter(c => c.isDigit)
    val twoDigits = firstTranslationNumbersOnly.charAt(0).toString + lastTranslationNumbersOnly.last.toString
    twoDigits.toInt
  }
}
