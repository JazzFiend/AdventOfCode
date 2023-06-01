package HeightMapRiskCalculator

import (
	"Day09_SmokeBasin/Common/FileHelpers"
	a "Day09_SmokeBasin/Common/TestAssertions"
	"testing"
)

func TestAcceptance(t *testing.T) {
	heightMap := []string{
		"2199943210",
		"3987894921",
		"9856789892",
		"8767896789",
		"9899965678",
	}
	a.AssertIntEqual(CalculateTotalRisk(heightMap), 15, t)
}

func TestPuzzlePartOne(t *testing.T) {
	heightMap, err := FileHelpers.ReadLines("../input.txt")
	if err != nil {
		panic(err)
	}
	a.AssertIntEqual(CalculateTotalRisk(heightMap), 585, t)
}
