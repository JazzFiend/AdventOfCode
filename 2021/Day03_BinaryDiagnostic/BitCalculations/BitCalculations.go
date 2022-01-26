package BitCalculations

import "strconv"

func CalculateMostCommonBitForPosition(position int, bytes []string) string {
	ones, zeroes := tallyBitsByPosition(position, bytes)
	if ones >= zeroes {
		return "1"
	} else {
		return "0"
	}
}

func CalculateLeastCommonBitForPosition(position int, bytes []string) string {
	ones, zeroes := tallyBitsByPosition(position, bytes)
	if ones < zeroes {
		return "1"
	} else {
		return "0"
	}
}

func tallyBitsByPosition(position int, bytes []string) (ones int, zeroes int) {
	for _, bytes := range bytes {
		if bytes[position] == '0' {
			zeroes += 1
		} else if bytes[position] == '1' {
			ones += 1
		}
	}
	return
}

func ConvertBinaryToInt(binary string) int {
	value, err := strconv.ParseInt(binary, 2, 64)
	if err != nil {
		panic(err.Error())
	}
	return int(value)
}

func ExtendBinaryWithZeroes(binary string, desiredLength int) (extended string) {
	extended = binary
	if len(extended) < desiredLength {
		originalLength := len(extended)
		for i := 0; i < desiredLength-originalLength; i++ {
			extended = "0" + extended
		}
	}
	return
}

func FlipBits(binary string) (flipped string) {
	for i := range binary {
		if binary[i] == '0' {
			flipped += "1"
		} else if binary[i] == '1' {
			flipped += "0"
		}
	}
	return
}
