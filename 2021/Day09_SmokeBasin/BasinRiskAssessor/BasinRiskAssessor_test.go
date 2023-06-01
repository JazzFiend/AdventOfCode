package BasinRiskAssessor

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
	a.AssertIntEqual(CalculateBasinRisk(heightMap), 1134, t)
}

func TestPuzzlePartTwo(t *testing.T) {
	heightMap, err := FileHelpers.ReadLines("../input.txt")
	if err != nil {
		panic(err)
	}
	a.AssertIntEqual(CalculateBasinRisk(heightMap), 827904, t)
}
