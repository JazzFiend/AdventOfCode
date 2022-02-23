package TestAssertions

import (
	"fmt"
	"testing"
)

func AssertIntEqual(received int, expected int, t *testing.T) {
	if received != expected {
		t.Errorf("Received: %d, Expected: %d", received, expected)
	}
}

func AssertFalse(received bool, t *testing.T) {
	if received {
		t.Errorf("Received True")
	}
}

func AssertTrue(received bool, t *testing.T) {
	if !received {
		t.Errorf("Received False")
	}
}

func AssertIntSliceEqual(received []int, expected []int, t *testing.T) {
	defer func() {
		if r := recover(); r != nil {
			t.Errorf("Arrays are not equal - Received: %v, Expected: %v", received, expected)
		}
	}()
	checkIntSliceEqual(received, expected)
}

func Assert2dIntSliceEqual(received [][]int, expected [][]int, t *testing.T) {
	defer func() {
		if r := recover(); r != nil {
			t.Errorf("Arrays are not equal - Received: %v, Expected: %v", received, expected)
		}
	}()
	for i, receivedRow := range received {
		checkIntSliceEqual(receivedRow, expected[i])
	}
}

func checkIntSliceEqual(received []int, expected []int) {
	if len(received) != len(expected) {
		panic(fmt.Sprintf("Lengths of lists not equal - Received: %d, Expected: %d", len(received), len(expected)))
	}
	for i := range received {
		if received[i] != expected[i] {
			panic(fmt.Sprintf("Element in list does not match - Received: %d, Expected: %d", received[i], expected[i]))
		}
	}
}
