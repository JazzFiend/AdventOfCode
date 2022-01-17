package Dive

import (
	"bufio"
	"os"
	"testing"
)

func verifySubLocation(received SubLocation, expected SubLocation, t *testing.T) {
	if received.forwardProgress != expected.forwardProgress {
		t.Errorf("Incorrect Forward Progress. Expected %d, Received: %d", expected.forwardProgress, received.forwardProgress)
	}

	if received.depth != expected.depth {
		t.Errorf("Incorrect Depth. Expected %d, Received: %d", expected.depth, received.depth)
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
