package Day07_TheTreacheryOfWhales

import (
	"Day07_TheTreacheryOfWhales/CrabPositionOptimizer"
	"Day07_TheTreacheryOfWhales/FileHelpers"
	a "Day07_TheTreacheryOfWhales/TestAssertions"
	"testing"
)

func TestOneCrabCrabEngineering(t *testing.T) {
	a.AssertIntEqual(CrabPositionOptimizer.CalculateOptimalFuelCrab([]int{5}), 0, t)
}

func TestTwoCrabsCrabEngineering(t *testing.T) {
	a.AssertIntEqual(CrabPositionOptimizer.CalculateOptimalFuelCrab([]int{2, 4}), 2, t)
}

func TestAcceptanceCrabEngineering(t *testing.T) {
	crabs := []int{16, 1, 2, 0, 4, 2, 7, 1, 2, 14}
	a.AssertIntEqual(CrabPositionOptimizer.CalculateOptimalFuelCrab(crabs), 168, t)
}

func TestPartTwo(t *testing.T) {
	crabs := FileHelpers.ReadLines("input.txt")
	a.AssertIntEqual(CrabPositionOptimizer.CalculateOptimalFuelCrab(crabs), 1, t)
}
