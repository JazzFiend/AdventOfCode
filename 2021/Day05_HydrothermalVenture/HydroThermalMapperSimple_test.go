package Day05_HydrothermalVenture

import (
	"Day05_HydrothermalVenture/HydrothermalMapper"
	assert "Day05_HydrothermalVenture/TestAssertions"
	"testing"
)

func TestSkipDiagonal(t *testing.T) {
	ventList := []string{"2,1 -> 2,0", "4,7 -> 11,5", "1,1 -> 3,1"}
	expected := [][]int{{0, 0, 1, 0}, {0, 1, 2, 1}}
	assert.Assert2dIntSliceEqual(HydrothermalMapper.MapVentsSimple(ventList), expected, t)
	assert.AssertIntEqual(HydrothermalMapper.CountOverlapsSimple(ventList), 1, t)
}

func TestAcceptanceSimple(t *testing.T) {
	ventList := []string{
		"0,9 -> 5,9",
		"8,0 -> 0,8",
		"9,4 -> 3,4",
		"2,2 -> 2,1",
		"7,0 -> 7,4",
		"6,4 -> 2,0",
		"0,9 -> 2,9",
		"3,4 -> 1,4",
		"0,0 -> 8,8",
		"5,5 -> 8,2",
	}
	expected := [][]int{
		{0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
		{0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
		{0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
		{0, 1, 1, 2, 1, 1, 1, 2, 1, 1},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{2, 2, 2, 1, 1, 1, 0, 0, 0, 0},
	}
	assert.Assert2dIntSliceEqual(HydrothermalMapper.MapVentsSimple(ventList), expected, t)
	assert.AssertIntEqual(HydrothermalMapper.CountOverlapsSimple(ventList), 5, t)
}

func TestPuzzleOne(t *testing.T) {
	ventList, _ := ReadLines("input.txt")
	assert.AssertIntEqual(HydrothermalMapper.CountOverlapsSimple(ventList), 6666, t)
}
