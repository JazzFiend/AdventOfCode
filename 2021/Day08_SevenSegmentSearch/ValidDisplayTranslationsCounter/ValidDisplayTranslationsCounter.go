package ValidDisplayTranslationsCounter

import (
	"Day08_SevenSegmentSearch/Common/CollectionHelpers"
	"Day08_SevenSegmentSearch/Common/MathHelpers"
	"Day08_SevenSegmentSearch/DisplayShape/DisplayShapeCreator"
	"Day08_SevenSegmentSearch/NumberValidator"
	"strings"
)

func CalculateOutputSum(signalEntries []string) int {
	permutations := MathHelpers.CalculatePermutations([]string{"a", "b", "c", "d", "e", "f", "g"})
	signalDisplayMapCombinations := buildAllDisplayMaps(permutations)
	formattedSignals := formatSignals(signalEntries)

	outputSum := 0
	for _, formattedSignal := range formattedSignals {
		outputValue := 0
		for _, signalDisplayMapCombination := range signalDisplayMapCombinations {
			if allSignalsValid(formattedSignal, signalDisplayMapCombination) {
				outputValue += NumberValidator.DecodeDisplay(DisplayShapeCreator.Create(signalDisplayMapCombination, formattedSignal[10])) * 1000
				outputValue += NumberValidator.DecodeDisplay(DisplayShapeCreator.Create(signalDisplayMapCombination, formattedSignal[11])) * 100
				outputValue += NumberValidator.DecodeDisplay(DisplayShapeCreator.Create(signalDisplayMapCombination, formattedSignal[12])) * 10
				outputValue += NumberValidator.DecodeDisplay(DisplayShapeCreator.Create(signalDisplayMapCombination, formattedSignal[13]))
				break
			}
		}
		outputSum += outputValue
	}
	return outputSum
}

func buildAllDisplayMaps(permutations [][]string) []map[string]string {
	signalDisplayMapCombinations := []map[string]string{}
	for _, permutation := range permutations {
		nextMap := make(map[string]string)
		nextMap["top"] = permutation[0]
		nextMap["upper-left"] = permutation[1]
		nextMap["upper-right"] = permutation[2]
		nextMap["middle"] = permutation[3]
		nextMap["lower-left"] = permutation[4]
		nextMap["lower-right"] = permutation[5]
		nextMap["bottom"] = permutation[6]

		nextMap[permutation[0]] = "top"
		nextMap[permutation[1]] = "upper-left"
		nextMap[permutation[2]] = "upper-right"
		nextMap[permutation[3]] = "middle"
		nextMap[permutation[4]] = "lower-left"
		nextMap[permutation[5]] = "lower-right"
		nextMap[permutation[6]] = "bottom"
		signalDisplayMapCombinations = append(signalDisplayMapCombinations, nextMap)
	}
	return signalDisplayMapCombinations
}

func formatSignals(signalEntries []string) [][]string {
	formattedSignals := [][]string{}
	for _, entry := range signalEntries {
		tokenizedEntry := strings.Split(entry, " ")
		formattedEntry := CollectionHelpers.RemoveElementFromSlice(tokenizedEntry, "|")
		formattedSignals = append(formattedSignals, formattedEntry)
	}
	return formattedSignals
}

func allSignalsValid(formattedSignal []string, signalDisplayMapCombination map[string]string) bool {
	allValid := true
	for _, signal := range formattedSignal {
		display := DisplayShapeCreator.Create(signalDisplayMapCombination, signal)
		if !NumberValidator.IsValidNumber(display) {
			allValid = false
			break
		}
	}
	return allValid
}
