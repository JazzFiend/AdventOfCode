package MathHelpers

import (
	a "Day09_SmokeBasin/Common/TestAssertions"
	"testing"
)

func TestAbsValuePositive(t *testing.T) {
	a.AssertIntEqual(AbsoluteValue(5), 5, t)
}

func TestAbsValueNegative(t *testing.T) {
	a.AssertIntEqual(AbsoluteValue(-12), 12, t)
}

func TestCalculateMin(t *testing.T) {
	a.AssertIntEqual(CalculateMin(3, 6), 3, t)
	a.AssertIntEqual(CalculateMin(-5, 0), -5, t)
	a.AssertIntEqual(CalculateMin(5, 5), 5, t)
}

func TestFindMaxValue(t *testing.T) {
	slice := []int{21, 34, 243, 223, -1}
	a.AssertIntEqual(FindMaxValue(slice), 243, t)
}

func TestConvertStringToInt(t *testing.T) {
	a.AssertIntEqual(ConvertStringToInt("4"), 4, t)
	a.AssertIntEqual(ConvertStringToInt("485"), 485, t)
	a.AssertIntEqual(ConvertStringToInt("-22"), -22, t)
	a.AssertIntEqual(ConvertStringToInt("0"), 0, t)
}

func TestPermutationEmptyList(t *testing.T) {
	a.Assert2dSliceEqual(CalculatePermutations([]int{}), [][]int{}, t)
}

func TestPermutationOneItem(t *testing.T) {
	a.Assert2dSliceEqual(CalculatePermutations([]int{2}), [][]int{{2}}, t)
}

func TestPermutationTwoItems(t *testing.T) {
	expected := [][]int{{5, 1}, {1, 5}}
	a.Assert2dSliceEqual(CalculatePermutations([]int{1, 5}), expected, t)
}

// TODO: This assertion requires the permutations to be in a specific order when they don't need to be. We can either
// convert the algorithm to use sets, or add an assertion that checks content and not order. I don't have the patience
// to do either of these right now.
func TestPermutationThreeItems(t *testing.T) {
	expected := [][]int{{3, 2, 1}, {2, 3, 1}, {3, 1, 2}, {1, 3, 2}, {2, 1, 3}, {1, 2, 3}}
	a.Assert2dSliceEqual(CalculatePermutations([]int{1, 2, 3}), expected, t)
}

func TestPermutationGeneric(t *testing.T) {
	expected := [][]string{{"c", "b", "a"}, {"b", "c", "a"}, {"c", "a", "b"}, {"a", "c", "b"}, {"b", "a", "c"}, {"a", "b", "c"}}
	a.Assert2dSliceEqual(CalculatePermutations([]string{"a", "b", "c"}), expected, t)
}

func TestPermutationAcceptance(t *testing.T) {
	a.AssertIntEqual(len(CalculatePermutations([]int{1, 2, 3, 4, 5, 6, 7})), 5040, t)
}
