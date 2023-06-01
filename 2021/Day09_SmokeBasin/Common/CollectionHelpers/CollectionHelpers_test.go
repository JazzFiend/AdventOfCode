package CollectionHelpers

import (
	a "Day09_SmokeBasin/Common/TestAssertions"
	"testing"
)

func TestContains(t *testing.T) {
	array := []int{1, 2, 3}
	toFind := 3
	a.AssertTrue(Contains(array, toFind), t)
}

func TestDoesNotContain(t *testing.T) {
	array := []int{1, 2, 3}
	toFind := 4
	a.AssertFalse(Contains(array, toFind), t)
}

func TestRemoveFromSlice(t *testing.T) {
	slice := []int{1, 2, 3}
	toRemove := 1
	slice = RemoveElementFromSlice(slice, toRemove)
	a.AssertFalse(Contains(slice, toRemove), t)
}
