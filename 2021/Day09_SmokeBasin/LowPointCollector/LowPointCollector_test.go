package LowPointCollector

import (
	a "Day09_SmokeBasin/Common/TestAssertions"
	"testing"
)

func TestEmptyHeightMap(t *testing.T) {
	a.AssertSliceEqual(CollectLowPoints([][]int{}), []int{}, t)
}

func TestSingleEntry(t *testing.T) {
	a.AssertSliceEqual(CollectLowPoints([][]int{{7}}), []int{7}, t)
}

func TestTwoEntriesSingleLine(t *testing.T) {
	a.AssertSliceEqual(CollectLowPoints([][]int{{3, 8}}), []int{3}, t)
	a.AssertSliceEqual(CollectLowPoints([][]int{{8, 3}}), []int{3}, t)
}

func TestManyEntriesSingleLine(t *testing.T) {
	a.AssertSliceEqual(CollectLowPoints([][]int{{9, 8, 4, 5, 8, 3, 5}}), []int{4, 3}, t)
}

func TestOneEntryManyLines(t *testing.T) {
	a.AssertSliceEqual(CollectLowPoints([][]int{{9}, {0}, {7}, {6}, {7}}), []int{0, 6}, t)
}

func TestAcceptance(t *testing.T) {
	heightMap := [][]int{
		{5, 3, 2},
		{4, 3, 5},
		{1, 4, 3},
	}
	expected := []int{2, 1, 3}
	a.AssertSliceEqual(CollectLowPoints(heightMap), expected, t)
}
