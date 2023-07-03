package LineValidator

import "Day10_SyntaxScoring/Common/Collections/Stack"

func ExtractIllegalCharacter(line string) string {
	illegal, _ := runThroughStack(line)
	return illegal
}

func ExtractIncompleteBrackets(line string) Stack.Stack[string] {
	_, incompletes := runThroughStack(line)
	return incompletes
}

// Rename me!
func runThroughStack(line string) (string, Stack.Stack[string]) {
	if len(line) < 1 {
		return "", Stack.New[string]()
	}

	bracketStack := Stack.New[string]()
	for i := 0; i < len(line); i++ {
		char := string(line[i])
		if isOpenBracket(char) {
			bracketStack.Push(char)
		} else {
			open, _ := bracketStack.Peek()
			if !bracketMatches(open, char) {
				return char, Stack.New[string]()
			} else {
				bracketStack.Pop()
			}
		}
	}
	return "", bracketStack
}

func isOpenBracket(char string) bool {
	return (char == "{" ||
		char == "(" ||
		char == "[" ||
		char == "<")
}

func bracketMatches(openBracket string, closeBracket string) bool {
	return (openBracket == "{" && closeBracket == "}") ||
		(openBracket == "(" && closeBracket == ")") ||
		(openBracket == "[" && closeBracket == "]") ||
		(openBracket == "<" && closeBracket == ">")
}
