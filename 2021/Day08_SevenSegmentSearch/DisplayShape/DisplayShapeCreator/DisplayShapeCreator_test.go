package DisplayShapeCreator

import (
	d "Day08_SevenSegmentSearch/DisplayShape"
	"testing"
)

func TestEmptySignalMap(t *testing.T) {
	received := Create(make(map[string]string), "bde")
	expected := d.DisplayShape{
		Top:        false,
		UpperLeft:  false,
		UpperRight: false,
		Middle:     false,
		LowerLeft:  false,
		LowerRight: false,
		Bottom:     false,
	}

	assertDisplayShapeEquals(received, expected, t)
}

func TestEmptySignal(t *testing.T) {
	signalMap := make(map[string]string)
	signalMap["a"] = "top"
	signalMap["b"] = "upper-left"
	signalMap["c"] = "upper-right"
	signalMap["d"] = "middle"
	signalMap["e"] = "lower-left"
	signalMap["f"] = "lower-right"
	signalMap["g"] = "bottom"

	received := Create(signalMap, "")
	expected := d.DisplayShape{
		Top:        false,
		UpperLeft:  false,
		UpperRight: false,
		Middle:     false,
		LowerLeft:  false,
		LowerRight: false,
		Bottom:     false,
	}

	assertDisplayShapeEquals(received, expected, t)
}

func TestTurnOnAll(t *testing.T) {
	signalMap := make(map[string]string)
	signalMap["a"] = "top"
	signalMap["b"] = "bottom"
	signalMap["c"] = "upper-right"
	signalMap["d"] = "lower-right"
	signalMap["e"] = "upper-left"
	signalMap["f"] = "middle"
	signalMap["g"] = "lower-left"

	received := Create(signalMap, "faegbcd")
	expected := d.DisplayShape{
		Top:        true,
		UpperLeft:  true,
		UpperRight: true,
		Middle:     true,
		LowerLeft:  true,
		LowerRight: true,
		Bottom:     true,
	}

	assertDisplayShapeEquals(received, expected, t)
}

func TestTurnOnSome(t *testing.T) {
	signalMap := make(map[string]string)
	signalMap["a"] = "top"
	signalMap["b"] = "bottom"
	signalMap["c"] = "upper-right"
	signalMap["d"] = "lower-right"
	signalMap["e"] = "upper-left"
	signalMap["f"] = "middle"
	signalMap["g"] = "lower-left"

	received := Create(signalMap, "edfc")
	expected := d.DisplayShape{
		Top:        false,
		UpperLeft:  true,
		UpperRight: true,
		Middle:     true,
		LowerLeft:  false,
		LowerRight: true,
		Bottom:     false,
	}

	assertDisplayShapeEquals(received, expected, t)
}

func assertDisplayShapeEquals(received d.DisplayShape, expected d.DisplayShape, t *testing.T) {
	equals := (received.Top == expected.Top &&
		received.UpperLeft == expected.UpperLeft &&
		received.UpperRight == expected.UpperRight &&
		received.Middle == expected.Middle &&
		received.LowerLeft == expected.LowerLeft &&
		received.LowerRight == expected.LowerRight &&
		received.Bottom == expected.Bottom)

	if !equals {
		t.Errorf("Display Shapes are not equal - Received: %v, Expected: %v", received, expected)
	}
}
