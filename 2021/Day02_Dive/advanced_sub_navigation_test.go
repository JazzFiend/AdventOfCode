package Dive

import (
	"testing"
)

func TestAdvancedEmptyInstructionList(t *testing.T) {
	nav := AdvancedSubNavigation{}
	subInstructions := []string{}
	finalSubLocation := nav.DriveSub(subInstructions)
	expectedLocation := SubLocation{depth: 0, forwardProgress: 0}
	verifySubLocation(finalSubLocation, expectedLocation, t)
}

func TestAdvancedOneForwardMovement(t *testing.T) {
	nav := AdvancedSubNavigation{}
	subInstructions := []string{"forward 1"}
	finalSubLocation := nav.DriveSub(subInstructions)
	expectedLocation := SubLocation{depth: 0, forwardProgress: 1}
	verifySubLocation(finalSubLocation, expectedLocation, t)
}

func TestAdvancedDown(t *testing.T) {
	nav := AdvancedSubNavigation{}
	subInstructions := []string{"down 8", "forward 2"}
	finalSubLocation := nav.DriveSub(subInstructions)
	expectedLocation := SubLocation{depth: 16, forwardProgress: 2}
	verifySubLocation(finalSubLocation, expectedLocation, t)
}

func TestAdvancedOneUp(t *testing.T) {
	nav := AdvancedSubNavigation{}
	subInstructions := []string{"up 3", "forward 2"}
	finalSubLocation := nav.DriveSub(subInstructions)
	expectedLocation := SubLocation{depth: -6, forwardProgress: 2}
	verifySubLocation(finalSubLocation, expectedLocation, t)
}

func TestAdvancedManyInstructions(t *testing.T) {
	nav := AdvancedSubNavigation{}
	subInstructions := []string{"up 1", "forward 2", "down 4", "forward 2"}
	finalSubLocation := nav.DriveSub(subInstructions)
	expectedLocation := SubLocation{depth: 4, forwardProgress: 4}
	verifySubLocation(finalSubLocation, expectedLocation, t)
}

func TestAdvancedAcceptance(t *testing.T) {
	nav := AdvancedSubNavigation{}
	subInstructions := []string{
		"forward 5",
		"down 5",
		"forward 8",
		"up 3",
		"down 8",
		"forward 2",
	}
	finalSubLocation := nav.DriveSub(subInstructions)
	expectedLocation := SubLocation{depth: 60, forwardProgress: 15}
	verifySubLocation(finalSubLocation, expectedLocation, t)
}

func TestPuzzleTwo(t *testing.T) {
	nav := AdvancedSubNavigation{}
	subInstructions, _ := readLines("input.txt")
	finalSubLocation := nav.DriveSub(subInstructions)
	expectedLocation := SubLocation{depth: 1000556, forwardProgress: 1970}
	verifySubLocation(finalSubLocation, expectedLocation, t)
}
