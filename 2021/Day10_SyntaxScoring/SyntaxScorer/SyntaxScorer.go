package SyntaxScorer

import "Day10_SyntaxScoring/Common/Collections/Stack"

func ScoreErrors(corruptedCharacters []string) int {
	total := 0
	for _, corrupt := range corruptedCharacters {
		if corrupt == ")" {
			total += 3
		} else if corrupt == "]" {
			total += 57
		} else if corrupt == "}" {
			total += 1197
		} else if corrupt == ">" {
			total += 25137
		}
	}
	return total
}

func ScoreAutocomplete(corrections []Stack.Stack[string]) []int {
	scores := []int{}
	for _, correctionStack := range corrections {
		total := 0
		for correctionStack.Length() > 0 {
			char := correctionStack.Pop()
			total = total*5 + getAutocompleteCharacterScore(char)
		}
		scores = append(scores, total)
	}
	return scores
}

func getAutocompleteCharacterScore(char string) int {
	if char == "(" {
		return 1
	} else if char == "[" {
		return 2
	} else if char == "{" {
		return 3
	} else if char == "<" {
		return 4
	}
	// Good input will never return this
	return 0
}
