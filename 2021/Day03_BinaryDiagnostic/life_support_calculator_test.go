package Day03_BinaryDiagnostic

import (
	"Day03_BinaryDiagnostic/FilterStrategy"
	life "Day03_BinaryDiagnostic/LifeSupportCalculator"
	"testing"
)

func TestEmptyReportLifeSupport(t *testing.T) {
	var oxygen int = life.CalculateRate([]string{}, FilterStrategy.OxygenFilter{})
	assertEqual(0, oxygen, t)

	var co2 int = life.CalculateRate([]string{}, FilterStrategy.CO2Filter{})
	assertEqual(0, co2, t)
}

func TestOneReportLifeSupport(t *testing.T) {
	report := []string{"11010"}
	var oxygen int = life.CalculateRate(report, FilterStrategy.OxygenFilter{})
	assertEqual(26, oxygen, t)

	var co2 int = life.CalculateRate(report, FilterStrategy.CO2Filter{})
	assertEqual(26, co2, t)
}

func TestTieBreaker(t *testing.T) {
	report := []string{"0010", "1001"}
	var oxygen int = life.CalculateRate(report, FilterStrategy.OxygenFilter{})
	assertEqual(9, oxygen, t)

	var co2 int = life.CalculateRate(report, FilterStrategy.CO2Filter{})
	assertEqual(2, co2, t)
}

func TestDecideOnSecondBit(t *testing.T) {
	report := []string{"1010", "1101"}
	var oxygen int = life.CalculateRate(report, FilterStrategy.OxygenFilter{})
	assertEqual(13, oxygen, t)

	report = []string{"0000", "0111"}
	var co2 int = life.CalculateRate(report, FilterStrategy.CO2Filter{})
	assertEqual(0, co2, t)
}

func TestAcceptanceLifeSupport(t *testing.T) {
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

	var oxygen int = life.CalculateRate(report, FilterStrategy.OxygenFilter{})
	assertEqual(23, oxygen, t)

	var co2 int = life.CalculateRate(report, FilterStrategy.CO2Filter{})
	assertEqual(10, co2, t)
}

func TestPuzzleTwo(t *testing.T) {
	report, _ := readLines("input.txt")
	var oxygen int = life.CalculateRate(report, FilterStrategy.OxygenFilter{})
	assertEqual(2526, oxygen, t)

	var co2 int = life.CalculateRate(report, FilterStrategy.CO2Filter{})
	assertEqual(1184, co2, t)
}
