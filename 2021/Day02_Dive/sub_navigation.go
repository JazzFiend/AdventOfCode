package Dive

import (
	"fmt"
	"strconv"
)

type SubNavigation interface {
	driveSub(instructions []string) SubLocation
}

type SubLocation struct {
	depth           int
	forwardProgress int
}

func extractInstructionValue(valueString string) int {
	value, err := strconv.Atoi(valueString)
	handleAtoiError(err)
	return value
}

func handleAtoiError(err error) {
	if err != nil {
		panic(fmt.Sprintf("Bad Integer conversion: %s", err.Error()))
	}
}
