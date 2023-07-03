package Converters

import "Day10_SyntaxScoring/Common/MathHelpers"

func StringSliceTo2dIntSlice(stringSlice []string) [][]int {
	intSlice2d := [][]int{}
	if len(stringSlice) > 0 {
		for i, str := range stringSlice {
			intSlice2d = append(intSlice2d, []int{})
			for j := 0; j < len(str); j++ {
				intSlice2d[i] = append(intSlice2d[i], extractSingleDigit(str, j))
			}
		}
	}
	return intSlice2d
}

func extractSingleDigit(stringSlice string, i int) int {
	return MathHelpers.ConvertStringToInt(string(stringSlice[i]))
}
