package BasinSizeCalculator

import (
	a "Day09_SmokeBasin/Common/TestAssertions"
	"testing"
)

func TestEmptyMap(t *testing.T) {
	a.AssertSliceEqual(CalculateBasinSizes([][]int{}), []int{}, t)
}

func TestOneHeight(t *testing.T) {
	a.AssertSliceEqual(CalculateBasinSizes([][]int{{5}}), []int{1}, t)
}

func TestOneNine(t *testing.T) {
	a.AssertSliceEqual(CalculateBasinSizes([][]int{{9}}), []int{}, t)
}

func TestTwoHeightsNoNines(t *testing.T) {
	a.AssertSliceEqual(CalculateBasinSizes([][]int{{7, 5}}), []int{2}, t)
}

func TestTwoHeightsOneNine(t *testing.T) {
	a.AssertSliceEqual(CalculateBasinSizes([][]int{{9, 4}}), []int{1}, t)
}

func TestTwoBasinsOneLine(t *testing.T) {
	a.AssertSliceEqual(CalculateBasinSizes([][]int{{7, 9, 4}}), []int{1, 1}, t)
}

func TestTwoLargeBasinsOneLine(t *testing.T) {
	a.AssertSliceEqual(CalculateBasinSizes([][]int{{0, 8, 6, 9, 8, 2}}), []int{3, 2}, t)
}

func TestTwoHeightsVertical(t *testing.T) {
	a.AssertSliceEqual(CalculateBasinSizes([][]int{{7}, {5}}), []int{2}, t)
}

func TestTwoHeightsVerticalOneNine(t *testing.T) {
	a.AssertSliceEqual(CalculateBasinSizes([][]int{{9}, {3}}), []int{1}, t)
}

func TestTwoBasinsOneLineVertical(t *testing.T) {
	a.AssertSliceEqual(CalculateBasinSizes([][]int{{3}, {9}, {6}}), []int{1, 1}, t)
}

func TestTwoLargeBasinsOneVerticalLine(t *testing.T) {
	a.AssertSliceEqual(CalculateBasinSizes([][]int{{6}, {8}, {3}, {9}, {4}, {8}}), []int{3, 2}, t)
}

func Test2dHeightMapOneBigBasin(t *testing.T) {
	heightMap := [][]int{
		{5, 3, 4},
		{0, 4, 3},
		{7, 8, 5},
	}
	a.AssertSliceEqual(CalculateBasinSizes(heightMap), []int{9}, t)
}

func Test2dHeightMapMultipleBasins(t *testing.T) {
	heightMap := [][]int{
		{5, 3, 4},
		{9, 9, 3},
		{7, 9, 5},
	}
	a.AssertSliceEqual(CalculateBasinSizes(heightMap), []int{5, 1}, t)
}

func Test2dHeightMapSpiralBasin(t *testing.T) {
	heightMap := [][]int{
		{5, 3, 4, 6},
		{9, 9, 9, 7},
		{9, 3, 9, 8},
		{9, 8, 5, 2},
	}
	a.AssertSliceEqual(CalculateBasinSizes(heightMap), []int{10}, t)
}

// Remember to check spiral basin
