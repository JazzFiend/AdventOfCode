package sonarSweep

import (
	"fmt"
	"io"
	"os"
	"testing"
)

func assertEqual(received int, expected int, t *testing.T) {
	if received != expected {
		t.Errorf("Received: %d, Expected: %d", received, expected)
	}
}

func readFile(filePath string) (numbers []int) {
	fd, err := os.Open(filePath)
	if err != nil {
		panic(fmt.Sprintf("open %s: %v", filePath, err))
	}
	var line int
	for {

		_, err := fmt.Fscanf(fd, "%d\n", &line)

		if err != nil {
			fmt.Println(err)
			if err == io.EOF {
				return
			}
			panic(fmt.Sprintf("Scan Failed %s: %v", filePath, err))

		}
		numbers = append(numbers, line)
	}
}

func TestEmptyDepthList(t *testing.T) {
	depths := []int{}
	assertEqual(countDepthIncreases(depths), 0, t)
}

func TestOneDepth(t *testing.T) {
	depths := []int{50}
	assertEqual(countDepthIncreases(depths), 0, t)
}

func TestOneIncrease(t *testing.T) {
	depths := []int{50, 100}
	assertEqual(countDepthIncreases(depths), 1, t)
}

func TestAcceptance(t *testing.T) {
	depths := []int{199, 200, 208, 210, 200, 207, 240, 269, 260, 263}
	assertEqual(countDepthIncreases(depths), 7, t)
}

func TestPuzzleOne(t *testing.T) {
	depths := readFile("input.txt")
	assertEqual(countDepthIncreases(depths), 1529, t)
}

func TestTwoWindows(t *testing.T) {
	depths := []int{1, 2, 3, 4}
	assertEqual(countDepthIncreasesSlidingWindow(depths), 1, t)
}

func TestAcceptanceSlidingWindow(t *testing.T) {
	depths := []int{199, 200, 208, 210, 200, 207, 240, 269, 260, 263}
	assertEqual(countDepthIncreasesSlidingWindow(depths), 5, t)
}

func TestPuzzleTwo(t *testing.T) {
	depths := readFile("input.txt")
	assertEqual(countDepthIncreasesSlidingWindow(depths), 1567, t)
}
