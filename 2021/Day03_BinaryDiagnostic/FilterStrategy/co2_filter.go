package FilterStrategy

import "Day03_BinaryDiagnostic/BitCalculations"

type CO2Filter struct{}

func (f CO2Filter) Filter(report []string, bitPosition int) []string {
	var mostCommonBit string = BitCalculations.CalculateLeastCommonBitForPosition(bitPosition, report)
	return filterByBitAndPosition(report, mostCommonBit, bitPosition)
}
