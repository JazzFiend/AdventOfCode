package CollectionHelpers

import (
	a "Day08_SevenSegmentSearch/Common/TestAssertions"
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
