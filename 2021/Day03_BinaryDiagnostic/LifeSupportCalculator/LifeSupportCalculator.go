package LifeSupportCalculator

import (
	"Day03_BinaryDiagnostic/BitCalculations"
	"Day03_BinaryDiagnostic/FilterStrategy"
)

func CalculateRate(report []string, f FilterStrategy.FilterStrategy) int {
	oxygenGeneratorRate := "0"
	if len(report) == 0 {
		return 0
	}
	if len(report) == 1 {
		return BitCalculations.ConvertBinaryToInt(report[0])
	}

	newReport := report
	for i := 0; i < len(report[0]); i++ {
		newReport = f.Filter(newReport, i)
		if len(newReport) == 1 {
			oxygenGeneratorRate = newReport[0]
			break
		}
	}
	return BitCalculations.ConvertBinaryToInt(oxygenGeneratorRate)
}
