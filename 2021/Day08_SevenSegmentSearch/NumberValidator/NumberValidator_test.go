package NumberValidator

import (
	a "Day08_SevenSegmentSearch/Common/TestAssertions"
	d "Day08_SevenSegmentSearch/DisplayShape"
	"testing"
)

func TestValidNumbers(t *testing.T) {
	a.AssertTrue(IsValidNumber(generateZero()), t)
	a.AssertTrue(IsValidNumber(generateOne()), t)
	a.AssertTrue(IsValidNumber(generateTwo()), t)
	a.AssertTrue(IsValidNumber(generateThree()), t)
	a.AssertTrue(IsValidNumber(generateFour()), t)
	a.AssertTrue(IsValidNumber(generateFive()), t)
	a.AssertTrue(IsValidNumber(generateSix()), t)
	a.AssertTrue(IsValidNumber(generateSeven()), t)
	a.AssertTrue(IsValidNumber(generateEight()), t)
	a.AssertTrue(IsValidNumber(generateNine()), t)
}

func TestInvalidNumbers(t *testing.T) {
	first := d.DisplayShape{
		Top:        false,
		UpperLeft:  true,
		UpperRight: false,
		Middle:     false,
		LowerLeft:  true,
		LowerRight: false,
		Bottom:     false,
	}

	second := d.DisplayShape{
		Top:        false,
		UpperLeft:  true,
		UpperRight: true,
		Middle:     false,
		LowerLeft:  true,
		LowerRight: true,
		Bottom:     false,
	}

	third := d.DisplayShape{
		Top:        true,
		UpperLeft:  false,
		UpperRight: false,
		Middle:     true,
		LowerLeft:  false,
		LowerRight: false,
		Bottom:     true,
	}

	a.AssertFalse(IsValidNumber(first), t)
	a.AssertFalse(IsValidNumber(second), t)
	a.AssertFalse(IsValidNumber(third), t)
}

func generateZero() d.DisplayShape {
	return d.DisplayShape{
		Top:        true,
		UpperLeft:  true,
		UpperRight: true,
		Middle:     false,
		LowerLeft:  true,
		LowerRight: true,
		Bottom:     true,
	}
}
func generateOne() d.DisplayShape {
	return d.DisplayShape{
		Top:        false,
		UpperLeft:  false,
		UpperRight: true,
		Middle:     false,
		LowerLeft:  false,
		LowerRight: true,
		Bottom:     false,
	}
}
func generateTwo() d.DisplayShape {
	return d.DisplayShape{
		Top:        true,
		UpperLeft:  false,
		UpperRight: true,
		Middle:     true,
		LowerLeft:  true,
		LowerRight: false,
		Bottom:     true,
	}
}
func generateThree() d.DisplayShape {
	return d.DisplayShape{
		Top:        true,
		UpperLeft:  false,
		UpperRight: true,
		Middle:     true,
		LowerLeft:  false,
		LowerRight: true,
		Bottom:     true,
	}
}
func generateFour() d.DisplayShape {
	return d.DisplayShape{
		Top:        false,
		UpperLeft:  true,
		UpperRight: true,
		Middle:     true,
		LowerLeft:  false,
		LowerRight: true,
		Bottom:     false,
	}
}
func generateFive() d.DisplayShape {
	return d.DisplayShape{
		Top:        true,
		UpperLeft:  true,
		UpperRight: false,
		Middle:     true,
		LowerLeft:  false,
		LowerRight: true,
		Bottom:     true,
	}
}
func generateSix() d.DisplayShape {
	return d.DisplayShape{
		Top:        true,
		UpperLeft:  true,
		UpperRight: false,
		Middle:     true,
		LowerLeft:  true,
		LowerRight: true,
		Bottom:     true,
	}
}
func generateSeven() d.DisplayShape {
	return d.DisplayShape{
		Top:        true,
		UpperLeft:  false,
		UpperRight: true,
		Middle:     false,
		LowerLeft:  false,
		LowerRight: true,
		Bottom:     false,
	}
}

func generateEight() d.DisplayShape {
	return d.DisplayShape{
		Top:        true,
		UpperLeft:  true,
		UpperRight: true,
		Middle:     true,
		LowerLeft:  true,
		LowerRight: true,
		Bottom:     true,
	}
}

func generateNine() d.DisplayShape {
	return d.DisplayShape{
		Top:        true,
		UpperLeft:  true,
		UpperRight: true,
		Middle:     true,
		LowerLeft:  false,
		LowerRight: true,
		Bottom:     true,
	}
}
