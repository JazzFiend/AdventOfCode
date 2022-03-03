package Day07_TheTreacheryOfWhales

import (
	"Day07_TheTreacheryOfWhales/CrabPositionOptimizer"
	"Day07_TheTreacheryOfWhales/FileHelpers"
	a "Day07_TheTreacheryOfWhales/TestAssertions"
	"testing"
)

func TestOneCrabSimple(t *testing.T) {
	a.AssertIntEqual(CrabPositionOptimizer.CalculateOptimalFuelSimple([]int{5}), 0, t)
}

func TestTwoCrabsSimple(t *testing.T) {
	a.AssertIntEqual(CrabPositionOptimizer.CalculateOptimalFuelSimple([]int{2, 4}), 2, t)
}

func TestAcceptanceSimple(t *testing.T) {
	crabs := []int{16, 1, 2, 0, 4, 2, 7, 1, 2, 14}
	a.AssertIntEqual(CrabPositionOptimizer.CalculateOptimalFuelSimple(crabs), 37, t)
}

func TestPartOne(t *testing.T) {
	crabs := FileHelpers.ReadLines("input.txt")
	a.AssertIntEqual(CrabPositionOptimizer.CalculateOptimalFuelSimple(crabs), 328187, t)
}
