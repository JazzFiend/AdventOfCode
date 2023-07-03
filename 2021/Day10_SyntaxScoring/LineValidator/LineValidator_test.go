package LineValidator

import (
	a "Day10_SyntaxScoring/Common/TestAssertions"
	"testing"
)

func TestBlankLine(t *testing.T) {
	a.AssertEqual[string](ExtractIllegalCharacter(""), "", t)
}

func TestCorrectLine(t *testing.T) {
	testCases := []string{"()", "[]", "{}", "<>"}

	for _, testCase := range testCases {
		a.AssertEqual[string](ExtractIllegalCharacter(testCase), "", t)
	}
}

func TestIncompleteLine(t *testing.T) {
	testCases := []string{"(", "[", "{", "<"}

	for _, testCase := range testCases {
		a.AssertEqual[string](ExtractIllegalCharacter(testCase), "", t)
	}
}

func TestTwoCharacters(t *testing.T) {
	a.AssertEqual[string](ExtractIllegalCharacter("{]"), "]", t)
	a.AssertEqual[string](ExtractIllegalCharacter("<]"), "]", t)
	a.AssertEqual[string](ExtractIllegalCharacter("(]"), "]", t)
	a.AssertEqual[string](ExtractIllegalCharacter("<}"), "}", t)
	a.AssertEqual[string](ExtractIllegalCharacter("(}"), "}", t)
	a.AssertEqual[string](ExtractIllegalCharacter("[}"), "}", t)
	a.AssertEqual[string](ExtractIllegalCharacter("{>"), ">", t)
	a.AssertEqual[string](ExtractIllegalCharacter("(>"), ">", t)
	a.AssertEqual[string](ExtractIllegalCharacter("[>"), ">", t)
	a.AssertEqual[string](ExtractIllegalCharacter("{)"), ")", t)
	a.AssertEqual[string](ExtractIllegalCharacter("<)"), ")", t)
	a.AssertEqual[string](ExtractIllegalCharacter("[)"), ")", t)
}

func TestBlankLineIncomplete(t *testing.T) {
	stack := ExtractIncompleteBrackets("")
	a.AssertEqual(stack.Length(), 0, t)
}

func TestOneCharIncomplete(t *testing.T) {
	stack := ExtractIncompleteBrackets("(")
	close, _ := stack.Peek()
	a.AssertEqual(close, "(", t)
	a.AssertEqual(stack.Length(), 1, t)

	stack = ExtractIncompleteBrackets("[")
	close, _ = stack.Peek()
	a.AssertEqual(close, "[", t)
	a.AssertEqual(stack.Length(), 1, t)

	stack = ExtractIncompleteBrackets("{")
	close, _ = stack.Peek()
	a.AssertEqual(close, "{", t)
	a.AssertEqual(stack.Length(), 1, t)

	stack = ExtractIncompleteBrackets("<")
	close, _ = stack.Peek()
	a.AssertEqual(close, "<", t)
	a.AssertEqual(stack.Length(), 1, t)
}

func TestTwoCharIncomplete(t *testing.T) {
	testCases := []string{"()", "[]", "{}", "<>"}

	for _, testCase := range testCases {
		stack := ExtractIncompleteBrackets(testCase)
		a.AssertEqual(stack.Length(), 0, t)
	}
}

func TestTwoCharCorruptIncomplete(t *testing.T) {
	stack := ExtractIncompleteBrackets("(}")
	a.AssertEqual(stack.Length(), 0, t)
}

func TestIncompleteAcceptance(t *testing.T) {
	testResults := []string{"<", "[", "{", "("}
	stack := ExtractIncompleteBrackets("([]{<>[{()}<")
	i := 0
	for stack.Length() != 0 {
		a.AssertEqual(stack.Pop(), testResults[i], t)
		i += 1
	}
	a.AssertEqual(stack.Length(), 0, t)
}
