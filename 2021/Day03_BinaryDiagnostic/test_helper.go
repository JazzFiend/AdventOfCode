package Day03_BinaryDiagnostic

import (
	"bufio"
	"os"
	"testing"
)

func assertEqual(expected int, received int, t *testing.T) {
	if expected != received {
		t.Errorf("Expected: %d, Received: %d", expected, received)
	}
}

func readLines(path string) ([]string, error) {
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
