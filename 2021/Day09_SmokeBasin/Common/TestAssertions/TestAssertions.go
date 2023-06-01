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

func AssertSliceEqual[G comparable](received []G, expected []G, t *testing.T) {
	defer func() {
		if r := recover(); r != nil {
			t.Errorf("Arrays are not equal - Received: %v, Expected: %v", received, expected)
		}
	}()
	checkSliceEqual(received, expected)
}

func checkSliceEqual[G comparable](received []G, expected []G) {
	if len(received) != len(expected) {
		panic(fmt.Sprintf("Lengths of lists not equal - Received: %d, Expected: %d", len(received), len(expected)))
	}
	for i := range received {
		if received[i] != expected[i] {
			panic(fmt.Sprintf("Element in list does not match - Received: %d, Expected: %d", received[i], expected[i]))
		}
	}
}

func Assert2dSliceEqual[G comparable](received [][]G, expected [][]G, t *testing.T) {
	defer func() {
		if r := recover(); r != nil {
			t.Errorf("Arrays are not equal - Received: %v, Expected: %v", received, expected)
		}
	}()
	check2dSliceEqual(received, expected)

}

func check2dSliceEqual[G comparable](received [][]G, expected [][]G) {
	if len(expected) != len(received) {
		panic(fmt.Sprintf("Lengths of lists not equal - Received: %d, Expected: %d", len(received), len(expected)))
	}
	for i, currentSlice := range received {
		checkSliceEqual(currentSlice, expected[i])
	}
}
