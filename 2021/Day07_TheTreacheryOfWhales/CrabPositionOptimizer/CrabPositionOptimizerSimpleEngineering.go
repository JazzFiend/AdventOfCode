package CrabPositionOptimizer

import "Day07_TheTreacheryOfWhales/MathHelpers"

func CalculateOptimalFuelSimple(crabSlice []int) int {
	min := MathHelpers.MaxInt
	upperBound := MathHelpers.FindMaxValue(crabSlice)

	for i := 0; i <= upperBound; i++ {
		total := 0
		for _, crab := range crabSlice {
			total += MathHelpers.AbsoluteValue(crab - i)
		}
		min = MathHelpers.CalculateMin(min, total)
	}
	return min
}
