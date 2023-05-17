package NumberValidator

import displayShape "Day08_SevenSegmentSearch/DisplayShape"

func IsValidNumber(number displayShape.DisplayShape) bool {
	return (isZero(number) ||
		isOne(number) ||
		isTwo(number) ||
		isThree(number) ||
		isFour(number) ||
		isFive(number) ||
		isSix(number) ||
		isSeven(number) ||
		isEight(number) ||
		isNine(number))
}

func DecodeDisplay(number displayShape.DisplayShape) int {
	if isZero(number) {
		return 0
	} else if isOne(number) {
		return 1
	} else if isTwo(number) {
		return 2
	} else if isThree(number) {
		return 3
	} else if isFour(number) {
		return 4
	} else if isFive(number) {
		return 5
	} else if isSix(number) {
		return 6
	} else if isSeven(number) {
		return 7
	} else if isEight(number) {
		return 8
	} else if isNine(number) {
		return 9
	} else {
		return -1
	}
}

func isZero(number displayShape.DisplayShape) bool {
	return (number.Top &&
		number.UpperLeft &&
		number.UpperRight &&
		!number.Middle &&
		number.LowerLeft &&
		number.LowerRight &&
		number.Bottom)
}

func isOne(number displayShape.DisplayShape) bool {
	return (!number.Top &&
		!number.UpperLeft &&
		number.UpperRight &&
		!number.Middle &&
		!number.LowerLeft &&
		number.LowerRight &&
		!number.Bottom)
}

func isTwo(number displayShape.DisplayShape) bool {
	return (number.Top &&
		!number.UpperLeft &&
		number.UpperRight &&
		number.Middle &&
		number.LowerLeft &&
		!number.LowerRight &&
		number.Bottom)
}

func isThree(number displayShape.DisplayShape) bool {
	return (number.Top &&
		!number.UpperLeft &&
		number.UpperRight &&
		number.Middle &&
		!number.LowerLeft &&
		number.LowerRight &&
		number.Bottom)
}

func isFour(number displayShape.DisplayShape) bool {
	return (!number.Top &&
		number.UpperLeft &&
		number.UpperRight &&
		number.Middle &&
		!number.LowerLeft &&
		number.LowerRight &&
		!number.Bottom)
}

func isFive(number displayShape.DisplayShape) bool {
	return (number.Top &&
		number.UpperLeft &&
		!number.UpperRight &&
		number.Middle &&
		!number.LowerLeft &&
		number.LowerRight &&
		number.Bottom)
}

func isSix(number displayShape.DisplayShape) bool {
	return (number.Top &&
		number.UpperLeft &&
		!number.UpperRight &&
		number.Middle &&
		number.LowerLeft &&
		number.LowerRight &&
		number.Bottom)
}

func isSeven(number displayShape.DisplayShape) bool {
	return (number.Top &&
		!number.UpperLeft &&
		number.UpperRight &&
		!number.Middle &&
		!number.LowerLeft &&
		number.LowerRight &&
		!number.Bottom)
}

func isEight(number displayShape.DisplayShape) bool {
	return (number.Top &&
		number.UpperLeft &&
		number.UpperRight &&
		number.Middle &&
		number.LowerLeft &&
		number.LowerRight &&
		number.Bottom)
}

func isNine(number displayShape.DisplayShape) bool {
	return (number.Top &&
		number.UpperLeft &&
		number.UpperRight &&
		number.Middle &&
		!number.LowerLeft &&
		number.LowerRight &&
		number.Bottom)
}
