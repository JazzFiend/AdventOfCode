package CoordinateExtractor

import (
	"fmt"
	"strconv"
	"strings"
)

func ExtractStartAndFinish(ventLine string) LineCoordinate {
	start, finish := extractPoints(ventLine)
	startX, finishX := extractXCoordinates(start, finish)
	startY, finishY := extractYCoordinates(start, finish)

	return LineCoordinate{
		convertStringToInt(startX),
		convertStringToInt(startY),
		convertStringToInt(finishX),
		convertStringToInt(finishY),
	}
}

func extractPoints(ventLine string) (string, string) {
	startAndFinish := strings.Split(ventLine, " -> ")
	start := startAndFinish[0]
	finish := startAndFinish[1]
	return start, finish
}

func extractXCoordinates(start string, finish string) (string, string) {
	startX := strings.Split(start, ",")[0]
	finishX := strings.Split(finish, ",")[0]
	return startX, finishX
}

func extractYCoordinates(start string, finish string) (string, string) {
	startY := strings.Split(start, ",")[1]
	finishY := strings.Split(finish, ",")[1]
	return startY, finishY
}

func convertStringToInt(s string) int {
	i, err := strconv.Atoi(s)
	if err != nil {
		panic(fmt.Sprintf("Bad Integer conversion: %s", err.Error()))
	}
	return i
}

func NormalizeStartAndFinish(startInt int, finishInt int) (int, int) {
	if finishInt < startInt {
		temp := finishInt
		finishInt = startInt
		startInt = temp
	}
	return startInt, finishInt
}
