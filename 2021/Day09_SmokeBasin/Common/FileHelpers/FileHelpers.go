package FileHelpers

import (
	"Day09_SmokeBasin/Common/MathHelpers"
	"bufio"
	"os"
	"strings"
)

func ReadLinesAsInts(path string) []int {
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

func ReadLines(path string) ([]string, error) {
	file, err := os.Open(path)
	if err != nil {
		return nil, err
	}
	defer file.Close()

	var lines []string
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		lines = append(lines, scanner.Text())
	}
	return lines, scanner.Err()
}
