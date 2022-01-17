package Dive

import (
	"strings"
)

type AdvancedSubNavigation struct{ aim int }

func (nav *AdvancedSubNavigation) DriveSub(instructions []string) (subLocation SubLocation) {
	nav.aim = 0
	if len(instructions) > 0 {
		for _, instruction := range instructions {
			instructionParts := strings.Split(instruction, " ")
			value := extractInstructionValue(instructionParts[1])
			subLocation = nav.handleInstruction(instructionParts[0], value, subLocation)
		}
	}
	return
}

func (nav *AdvancedSubNavigation) handleInstruction(direction string, value int, subLocation SubLocation) (newLocation SubLocation) {
	newLocation = subLocation
	if direction == "forward" {
		newLocation.forwardProgress += value
		newLocation.depth += (nav.aim * value)
	} else if direction == "down" {
		nav.aim += value
	} else if direction == "up" {
		nav.aim -= value
	}
	return
}
