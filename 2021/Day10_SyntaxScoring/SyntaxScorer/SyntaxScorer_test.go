package SyntaxScorer

import (
	"Day10_SyntaxScoring/Common/Collections/Stack"
	a "Day10_SyntaxScoring/Common/TestAssertions"
	"testing"
)

func TestAutocomplete_NoValues(t *testing.T) {
	a.AssertSliceEqual(ScoreAutocomplete([]Stack.Stack[string]{}), []int{}, t)
}

func TestAutocomplete_OneLineOneChar(t *testing.T) {
	corrections := []Stack.Stack[string]{Stack.NewWithPrefilled[string]([]string{"("})}
	a.AssertSliceEqual(ScoreAutocomplete(corrections), []int{1}, t)

	corrections = []Stack.Stack[string]{Stack.NewWithPrefilled[string]([]string{"["})}
	a.AssertSliceEqual(ScoreAutocomplete(corrections), []int{2}, t)

	corrections = []Stack.Stack[string]{Stack.NewWithPrefilled[string]([]string{"{"})}
	a.AssertSliceEqual(ScoreAutocomplete(corrections), []int{3}, t)

	corrections = []Stack.Stack[string]{Stack.NewWithPrefilled[string]([]string{"<"})}
	a.AssertSliceEqual(ScoreAutocomplete(corrections), []int{4}, t)
}

func TestAutocomplete_OneLineManyChars(t *testing.T) {
	corrections := []Stack.Stack[string]{Stack.NewWithPrefilled[string]([]string{"(", "{", "[", "<"})}
	a.AssertSliceEqual(ScoreAutocomplete(corrections), []int{566}, t)
}

func TestAutocomplete_ManyLinesManyChars(t *testing.T) {
	corrections := []Stack.Stack[string]{
		Stack.NewWithPrefilled[string]([]string{"<", "{"}),
		Stack.NewWithPrefilled[string]([]string{"[", "["}),
		Stack.NewWithPrefilled[string]([]string{"(", "<", "("}),
	}
	a.AssertSliceEqual(ScoreAutocomplete(corrections), []int{19, 12, 46}, t)
}
