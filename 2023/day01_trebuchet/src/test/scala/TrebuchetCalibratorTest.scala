class TrebuchetCalibratorTest extends munit.FunSuite {
  test("No calibration values should return 0") {
    assertEquals(TrebuchetCalibrator.calculateCalibration(List.empty[String]), 0)
  }

  test("A calibration value with no extra characters should return the int version of itself") {
    val calibrations:List[String] = List("73")
    assertEquals(TrebuchetCalibrator.calculateCalibration(calibrations), 73)
  }

  test("A calibration value with extra characters should return a two digit int") {
    val calibrations: List[String] = List("5gnhrijij1")
    assertEquals(TrebuchetCalibrator.calculateCalibration(calibrations), 51)
  }

  test("A calibration value with extra characters before and after the numbers should return a two digit int") {
    val calibrations: List[String] = List("sd4vsfa7ferw")
    assertEquals(TrebuchetCalibrator.calculateCalibration(calibrations), 47)
  }

  test("A single number should be represented as two identical numbers") {
    val calibrations: List[String] = List("6")
    assertEquals(TrebuchetCalibrator.calculateCalibration(calibrations), 66)
  }

  test("A single number should work with a bunch of other characters") {
    val calibrations: List[String] = List("yrge2ude")
    assertEquals(TrebuchetCalibrator.calculateCalibration(calibrations), 22)
  }

  test("Only the first and last number should be used") {
    val calibrations: List[String] = List("743")
    assertEquals(TrebuchetCalibrator.calculateCalibration(calibrations), 73)
  }

  test("Several calibration values should be summed") {
    val calibrations: List[String] = List("tyht8394", "4u8w39", "jfg8w")
    assertEquals(TrebuchetCalibrator.calculateCalibration(calibrations), 84+49+88)
  }

  test("Acceptance Test") {
    val calibrations = List("1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet")
    assertEquals(TrebuchetCalibrator.calculateCalibration(calibrations), 142)
  }

  test("Puzzle One") {
    val calibrations = io.Source.fromFile("src/test/scala/input.txt").getLines.toList
    assertEquals(TrebuchetCalibrator.calculateCalibration(calibrations), 57346)
  }

  test("Acceptance Test 2") {
    val calibrations = List("two1nine", "eightwothree", "abcone2threexyz", "xtwone3four", "4nineeightseven2", "zoneight234", "7pqrstsixteen")
    assertEquals(TrebuchetCalibrator.calculateCalibrationWordy(calibrations), 281)
  }

  test("Puzzle Two") {
    val calibrations = io.Source.fromFile("src/test/scala/input.txt").getLines.toList
    assertEquals(TrebuchetCalibrator.calculateCalibrationWordy(calibrations), -1)
  }
}
