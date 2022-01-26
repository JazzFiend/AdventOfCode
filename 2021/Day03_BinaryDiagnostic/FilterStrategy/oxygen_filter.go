package FilterStrategy

import "Day03_BinaryDiagnostic/BitCalculations"

type OxygenFilter struct{}

func (f OxygenFilter) Filter(report []string, bitPosition int) []string {
	var mostCommonBit string = BitCalculations.CalculateMostCommonBitForPosition(bitPosition, report)
	return filterByBitAndPosition(report, mostCommonBit, bitPosition)
}
