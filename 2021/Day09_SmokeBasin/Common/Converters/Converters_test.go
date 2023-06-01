package Converters

import (
	a "Day09_SmokeBasin/Common/TestAssertions"
	"testing"
)

func TestStringSliceTo2dIntSlice_Empty(t *testing.T) {
	a.Assert2dSliceEqual(StringSliceTo2dIntSlice([]string{}), [][]int{}, t)
}

func TestStringSliceTo2dIntSlice_OneLineOneItem(t *testing.T) {
	a.Assert2dSliceEqual(StringSliceTo2dIntSlice([]string{"1"}), [][]int{{1}}, t)
}

func TestStringSliceTo2dIntSlice_OneLineManyItems(t *testing.T) {
	a.Assert2dSliceEqual(StringSliceTo2dIntSlice([]string{"56367"}), [][]int{{5, 6, 3, 6, 7}}, t)
}

func TestStringSliceTo2dIntSlice_ManyLinesOneItem(t *testing.T) {
	a.Assert2dSliceEqual(StringSliceTo2dIntSlice([]string{"6", "5", "7", "2", "8"}), [][]int{{6}, {5}, {7}, {2}, {8}}, t)
}

func TestStringSliceTo2dIntSlice_ManyLinesManyItems(t *testing.T) {
	strings := []string{
		"563",
		"327",
		"894",
	}
	expected := [][]int{
		{5, 6, 3},
		{3, 2, 7},
		{8, 9, 4},
	}
	a.Assert2dSliceEqual(StringSliceTo2dIntSlice(strings), expected, t)
}
