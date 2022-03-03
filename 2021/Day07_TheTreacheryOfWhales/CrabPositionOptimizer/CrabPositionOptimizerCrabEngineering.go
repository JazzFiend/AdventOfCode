package CrabPositionOptimizer

import "Day07_TheTreacheryOfWhales/MathHelpers"

func CalculateOptimalFuelCrab(crabSlice []int) int {
	min := MathHelpers.MaxInt
	upperBound := MathHelpers.FindMaxValue(crabSlice)

	for i := 0; i <= upperBound; i++ {
		total := 0
		for _, crab := range crabSlice {
			total += calculateFuelNeeded(crab, i)
		}
		min = MathHelpers.CalculateMin(min, total)
	}
	return min
}

func calculateFuelNeeded(start int, finish int) int {
	distance := MathHelpers.AbsoluteValue(start - finish)
	fuelConsumed := 0
	for i := 1; i <= distance; i++ {
		fuelConsumed += i
	}
	return fuelConsumed
}
