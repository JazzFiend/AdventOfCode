package NavigationLineScorer

import (
	"Day10_SyntaxScoring/Common/Collections/Stack"
	"Day10_SyntaxScoring/LineValidator"
	"Day10_SyntaxScoring/SyntaxScorer"
	"sort"
)

func ScoreCorrupted(navigationCode []string) int {
	corruptedCharacters := []string{}
	for _, line := range navigationCode {
		corrupted := LineValidator.ExtractIllegalCharacter(line)
		if corrupted != "" {
			corruptedCharacters = append(corruptedCharacters, corrupted)
		}
	}
	return SyntaxScorer.ScoreErrors(corruptedCharacters)
}

func ScoreIncomplete(navigationCode []string) int {
	incompletes := []Stack.Stack[string]{}
	for _, line := range navigationCode {
		if LineValidator.ExtractIllegalCharacter(line) == "" {
			incompletes = append(incompletes, LineValidator.ExtractIncompleteBrackets(line))
		}
	}

	scores := SyntaxScorer.ScoreAutocomplete(incompletes)
	sort.Slice(scores, func(i, j int) bool {
		return scores[i] < scores[j]
	})
	return scores[len(scores)/2]
}
