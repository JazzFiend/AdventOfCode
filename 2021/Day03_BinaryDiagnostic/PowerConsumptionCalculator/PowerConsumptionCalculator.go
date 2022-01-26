package PowerConsumptionCalculator

import (
	"Day03_BinaryDiagnostic/BitCalculations"
	"strconv"
)

func CalculateGammaRate(report []string) int {
	if len(report) == 0 {
		return 0
	}

	gammaRate := ""
	for i := 0; i < len(report[0]); i++ {
		gammaRate += BitCalculations.CalculateMostCommonBitForPosition(i, report)
	}

	return BitCalculations.ConvertBinaryToInt(gammaRate)
}

func CalculateEpsilonRateFromGamma(gammaRate int, entryLength int) int {
	var gammaBinary string = strconv.FormatInt(int64(gammaRate), 2)
	gammaBinary = BitCalculations.ExtendBinaryWithZeroes(gammaBinary, entryLength)
	var epsilonBinary string = BitCalculations.FlipBits(gammaBinary)
	return BitCalculations.ConvertBinaryToInt(epsilonBinary)
}
