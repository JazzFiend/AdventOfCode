package Dive

import (
	"testing"
)

func TestBasicEmptyInstructionList(t *testing.T) {
	nav := BasicSubNavigation{}
	subInstructions := []string{}
	finalSubLocation := nav.DriveSub(subInstructions)
	expectedLocation := SubLocation{depth: 0, forwardProgress: 0}
	verifySubLocation(finalSubLocation, expectedLocation, t)
}

func TestBasicOneForwardMovement(t *testing.T) {
	nav := BasicSubNavigation{}
	subInstructions := []string{"forward 1"}
	finalSubLocation := nav.DriveSub(subInstructions)
	expectedLocation := SubLocation{depth: 0, forwardProgress: 1}
	verifySubLocation(finalSubLocation, expectedLocation, t)
}

func TestOneDown(t *testing.T) {
	nav := BasicSubNavigation{}
	subInstructions := []string{"down 8"}
	finalSubLocation := nav.DriveSub(subInstructions)
	expectedLocation := SubLocation{depth: 8, forwardProgress: 0}
	verifySubLocation(finalSubLocation, expectedLocation, t)
}

func TestOneUp(t *testing.T) {
	nav := BasicSubNavigation{}
	subInstructions := []string{"up 3"}
	finalSubLocation := nav.DriveSub(subInstructions)
	expectedLocation := SubLocation{depth: -3, forwardProgress: 0}
	verifySubLocation(finalSubLocation, expectedLocation, t)
}

func TestManyInstructions(t *testing.T) {
	nav := BasicSubNavigation{}
	subInstructions := []string{"up 1", "down 1", "forward 1"}
	finalSubLocation := nav.DriveSub(subInstructions)
	expectedLocation := SubLocation{depth: 0, forwardProgress: 1}
	verifySubLocation(finalSubLocation, expectedLocation, t)
}

func TestAcceptance(t *testing.T) {
	nav := BasicSubNavigation{}
	subInstructions := []string{
		"forward 5",
		"down 5",
		"forward 8",
		"up 3",
		"down 8",
		"forward 2",
	}
	finalSubLocation := nav.DriveSub(subInstructions)
	expectedLocation := SubLocation{depth: 10, forwardProgress: 15}
	verifySubLocation(finalSubLocation, expectedLocation, t)
}

func TestPuzzleOne(t *testing.T) {
	nav := BasicSubNavigation{}
	subInstructions, _ := readLines("input.txt")
	finalSubLocation := nav.DriveSub(subInstructions)
	expectedLocation := SubLocation{depth: 916, forwardProgress: 1970}
	verifySubLocation(finalSubLocation, expectedLocation, t)
}
