package Day03_BinaryDiagnostic

import (
	power "Day03_BinaryDiagnostic/PowerConsumptionCalculator"
	"testing"
)

func TestEmptyReportPower(t *testing.T) {
	var gamma int = power.CalculateGammaRate([]string{})
	assertEqual(0, gamma, t)

	var epsilon int = power.CalculateEpsilonRateFromGamma(gamma, 5)
	assertEqual(31, epsilon, t)
}

func TestOneReportPower(t *testing.T) {
	var gamma int = power.CalculateGammaRate([]string{"01010"})
	assertEqual(10, gamma, t)

	var epsilon int = power.CalculateEpsilonRateFromGamma(gamma, 5)
	assertEqual(21, epsilon, t)
}

func TestMultipleReports(t *testing.T) {
	var gamma int = power.CalculateGammaRate([]string{"01000", "01101", "01111"})
	assertEqual(13, gamma, t)

	var epsilon int = power.CalculateEpsilonRateFromGamma(gamma, 5)
	assertEqual(18, epsilon, t)
}

func TestMoreThanFiveBits(t *testing.T) {
	var gamma int = power.CalculateGammaRate([]string{"110101000010"})
	assertEqual(3394, gamma, t)

	var epsilon int = power.CalculateEpsilonRateFromGamma(gamma, 12)
	assertEqual(701, epsilon, t)
}

func TestAcceptancePower(t *testing.T) {
	report := []string{
		"00100",
		"11110",
		"10110",
		"10111",
		"10101",
		"01111",
		"00111",
		"11100",
		"10000",
		"11001",
		"00010",
		"01010",
	}

	var gamma int = power.CalculateGammaRate(report)
	assertEqual(22, gamma, t)

	var epsilon int = power.CalculateEpsilonRateFromGamma(gamma, 5)
	assertEqual(9, epsilon, t)
}

func TestPuzzleOne(t *testing.T) {
	report, _ := readLines("input.txt")
	var gamma int = power.CalculateGammaRate(report)
	assertEqual(2663, gamma, t)

	var epsilon int = power.CalculateEpsilonRateFromGamma(gamma, 5)
	assertEqual(1432, epsilon, t)
}
