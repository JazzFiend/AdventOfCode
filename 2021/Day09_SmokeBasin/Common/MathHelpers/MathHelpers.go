package MathHelpers

import (
	"Day09_SmokeBasin/Common/CollectionHelpers"
	"math"
	"strconv"
)

const MaxInt = int(math.MaxUint >> 1)
const MinInt = -MaxInt - 1

func AbsoluteValue(n int) int {
	return int(math.Abs(float64(n)))
}

func CalculateMin(a int, b int) int {
	if a < b {
		return a
	} else {
		return b
	}
}

func FindMaxValue(s []int) int {
	max := MinInt
	for _, n := range s {
		if n > max {
			max = n
		}
	}
	return max
}

func ConvertStringToInt(s string) int {
	n, err := strconv.Atoi(s)
	if err != nil {
		panic(err)
	}
	return n
}

func CalculatePermutations[T comparable](elements []T) [][]T {
	permutationList := [][]T{}

	if len(elements) == 0 {
		return permutationList
	} else if len(elements) == 1 {
		permutationList = append(permutationList, []T{elements[0]})
	} else {
		for _, currentNumber := range elements {
			shortenedSlice := CollectionHelpers.RemoveElementFromSlice(elements, currentNumber)
			subsequentPermutations := CalculatePermutations(shortenedSlice)
			for i, permutation := range subsequentPermutations {
				subsequentPermutations[i] = append(permutation, currentNumber)
			}
			permutationList = append(permutationList, subsequentPermutations...)
		}
	}

	return permutationList
}
