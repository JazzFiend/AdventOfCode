package TestAssertions

import (
	"Day04_GiantSquid/Cell"
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

func AssertIntListDeep(received []int, expected []int, t *testing.T) {
	if len(received) != len(expected) {
		t.Errorf("Lists are not equal - Received: %v, Expected: %v", received, expected)
	}
	for i := range received {
		if received[i] != expected[i] {
			t.Errorf("Lists are not equal - Received: %v, Expected: %v", received, expected)
		}
	}
}

func AssertCellEqual(received Cell.Cell, expected Cell.Cell, t *testing.T) {
	if received.Value != expected.Value ||
		received.Marked != expected.Marked {
		t.Errorf("Received: %s, Expected: %s", received.String(), expected.String())
	}
}

func AssertGridEquals(received [][]Cell.Cell, expected [][]Cell.Cell, t *testing.T) {
	for y := range received {
		for x := range received[y] {
			AssertCellEqual(received[y][x], expected[y][x], t)
		}
	}
}
