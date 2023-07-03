package NavigationLineScorer

import (
	"Day10_SyntaxScoring/Common/FileHelpers"
	a "Day10_SyntaxScoring/Common/TestAssertions"
	"testing"
)

func TestCorruptedAcceptance(t *testing.T) {
	navigationCode := []string{
		"[({(<(())[]>[[{[]{<()<>>",
		"[(()[<>])]({[<{<<[]>>(",
		"{([(<{}[<>[]}>{[]{[(<()>",
		"(((({<>}<{<{<>}{[]{[]{}",
		"[[<[([]))<([[{}[[()]]]",
		"[{[{({}]{}}([{[{{{}}([]",
		"{<[[]]>}<{[{[{[]{()[[[]",
		"[<(<(<(<{}))><([]([]()",
		"<{([([[(<>()){}]>(<<{{",
		"<{([{{}}[<[[[<>{}]]]>[]]",
	}

	a.AssertEqual[int](ScoreCorrupted(navigationCode), 26397, t)
}

func TestPuzzlePartOne(t *testing.T) {
	navigationCode, err := FileHelpers.ReadLines("../input.txt")
	if err != nil {
		panic(err)
	}
	a.AssertEqual(ScoreCorrupted(navigationCode), 358737, t)
}

func TestIncompleteAcceptance(t *testing.T) {
	navigationCode := []string{
		"[({(<(())[]>[[{[]{<()<>>",
		"[(()[<>])]({[<{<<[]>>(",
		"{([(<{}[<>[]}>{[]{[(<()>",
		"(((({<>}<{<{<>}{[]{[]{}",
		"[[<[([]))<([[{}[[()]]]",
		"[{[{({}]{}}([{[{{{}}([]",
		"{<[[]]>}<{[{[{[]{()[[[]",
		"[<(<(<(<{}))><([]([]()",
		"<{([([[(<>()){}]>(<<{{",
		"<{([{{}}[<[[[<>{}]]]>[]]",
	}

	a.AssertEqual[int](ScoreIncomplete(navigationCode), 288957, t)
}

func TestPuzzlePartTwo(t *testing.T) {
	navigationCode, err := FileHelpers.ReadLines("../input.txt")
	if err != nil {
		panic(err)
	}
	a.AssertEqual(ScoreIncomplete(navigationCode), -1, t)
}
