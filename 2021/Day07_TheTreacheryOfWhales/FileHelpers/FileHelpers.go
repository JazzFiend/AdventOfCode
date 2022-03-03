package FileHelpers

import (
	"Day07_TheTreacheryOfWhales/MathHelpers"
	"bufio"
	"os"
	"strings"
)

func ReadLines(path string) []int {
	file, err := os.Open(path)
	if err != nil {
		panic(err)
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)
	scanner.Scan()
	line := scanner.Text()

	numbers := []int{}

	tokenizedLine := strings.Split(line, ",")

	for _, s := range tokenizedLine {
		numbers = append(numbers, MathHelpers.ConvertStringToInt(s))
	}

	return numbers
}
