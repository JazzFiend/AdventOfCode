package Day04_GiantSquid

import (
	b "Day04_GiantSquid/Board"
	gc "Day04_GiantSquid/GridConstructor"
	"bufio"
	"io"
	"os"
	"strconv"
	"strings"
)

func readCalledNumbers(path string) []int {
	firstLine := readFirstLine(path)
	return convertStringToInts(firstLine)
}

func readFirstLine(path string) string {
	file := openFile(path)
	defer file.Close()

	scanner := bufio.NewScanner(file)
	scanner.Scan()
	return scanner.Text()
}

func convertStringToInts(commaSeperatedString string) []int {
	numberStrings := strings.Split(commaSeperatedString, ",")
	numberInts := []int{}
	for _, s := range numberStrings {
		n, err := strconv.Atoi(s)
		if err != nil {
			panic(err)
		}
		numberInts = append(numberInts, n)
	}
	return numberInts
}

func readBoards(path string) []b.Board {
	file := openFile(path)
	defer file.Close()
	scanner := prepScannerForBoards(file)

	lines := []string{}
	boards := []b.Board{}
	for scanner.Scan() {
		nextLine := scanner.Text()
		if nextLine == "" {
			boards = append(boards, makeNewBoard(lines))
			lines = []string{}
		} else {
			lines = append(lines, nextLine)
		}
	}
	boards = append(boards, makeNewBoard(lines))
	return boards
}

func openFile(path string) *os.File {
	file, err := os.Open(path)
	if err != nil {
		panic(err)
	}
	return file
}

func prepScannerForBoards(file io.Reader) *bufio.Scanner {
	scanner := bufio.NewScanner(file)
	// First line is called numbers. Second line is empty. Just pass over them.
	scanner.Scan()
	scanner.Scan()
	return scanner
}

func makeNewBoard(lines []string) b.Board {
	gridToConstruct := stringSliceTo2dIntSlice(lines)
	return b.Board{Grid: gc.ConstructGrid(gridToConstruct)}
}

func stringSliceTo2dIntSlice(stringSlice []string) [][]int {
	intSlice2d := [][]int{}
	for _, str := range stringSlice {
		intSlice2d = append(intSlice2d, stringToIntSlice(str))
	}
	return intSlice2d
}

func stringToIntSlice(str string) []int {
	row := []int{}
	trimmedStrings := strings.Fields(str)
	for _, s := range trimmedStrings {
		row = append(row, convertStringToInt(s))
	}
	return row
}

func convertStringToInt(s string) int {
	n, err := strconv.Atoi(s)
	if err != nil {
		panic(err)
	}
	return n
}
