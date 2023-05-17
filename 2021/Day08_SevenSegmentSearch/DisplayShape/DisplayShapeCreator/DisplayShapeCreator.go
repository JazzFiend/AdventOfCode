package DisplayShapeCreator

import (
	d "Day08_SevenSegmentSearch/DisplayShape"
	"strings"
)

func Create(signalDisplayMap map[string]string, signal string) d.DisplayShape {
	shape := d.DisplayShape{
		Top:        false,
		UpperLeft:  false,
		UpperRight: false,
		Middle:     false,
		LowerLeft:  false,
		LowerRight: false,
		Bottom:     false,
	}

	if strings.Contains(signal, "a") {
		shape = turnOnDisplaySegment(signalDisplayMap["a"], shape)
	}
	if strings.Contains(signal, "b") {
		shape = turnOnDisplaySegment(signalDisplayMap["b"], shape)
	}
	if strings.Contains(signal, "c") {
		shape = turnOnDisplaySegment(signalDisplayMap["c"], shape)
	}
	if strings.Contains(signal, "d") {
		shape = turnOnDisplaySegment(signalDisplayMap["d"], shape)
	}
	if strings.Contains(signal, "e") {
		shape = turnOnDisplaySegment(signalDisplayMap["e"], shape)
	}
	if strings.Contains(signal, "f") {
		shape = turnOnDisplaySegment(signalDisplayMap["f"], shape)
	}
	if strings.Contains(signal, "g") {
		shape = turnOnDisplaySegment(signalDisplayMap["g"], shape)
	}
	return shape
}

func turnOnDisplaySegment(segment string, shape d.DisplayShape) d.DisplayShape {
	modifiedShape := shape
	if segment == "top" {
		modifiedShape.Top = true
	} else if segment == "upper-left" {
		modifiedShape.UpperLeft = true
	} else if segment == "upper-right" {
		modifiedShape.UpperRight = true
	} else if segment == "middle" {
		modifiedShape.Middle = true
	} else if segment == "lower-left" {
		modifiedShape.LowerLeft = true
	} else if segment == "lower-right" {
		modifiedShape.LowerRight = true
	} else if segment == "bottom" {
		modifiedShape.Bottom = true
	}

	return modifiedShape
}
