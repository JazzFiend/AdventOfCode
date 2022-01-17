package Dive

import (
	"strings"
)

type BasicSubNavigation struct{}

func (nav *BasicSubNavigation) DriveSub(instructions []string) (subLocation SubLocation) {
	if len(instructions) > 0 {
		for _, instruction := range instructions {
			instructionParts := strings.Split(instruction, " ")
			value := extractInstructionValue(instructionParts[1])
			subLocation = nav.handleInstruction(instructionParts[0], value, subLocation)
		}
	}
	return
}

func (nav *BasicSubNavigation) handleInstruction(direction string, value int, subLocation SubLocation) (newLocation SubLocation) {
	newLocation = subLocation
	if direction == "forward" {
		newLocation.forwardProgress += value
	} else if direction == "down" {
		newLocation.depth += value
	} else if direction == "up" {
		newLocation.depth -= value
	}
	return
}
