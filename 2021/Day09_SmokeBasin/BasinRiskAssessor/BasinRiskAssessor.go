package BasinRiskAssessor

import (
	"Day09_SmokeBasin/BasinSizeCalculator"
	"Day09_SmokeBasin/Common/Converters"
	"sort"
)

func CalculateBasinRisk(heightMap []string) int {
	processedHeightMap := Converters.StringSliceTo2dIntSlice(heightMap)
	basinSizes := BasinSizeCalculator.CalculateBasinSizes(processedHeightMap)
	print(basinSizes)

	sort.Slice(basinSizes, func(i, j int) bool {
		return basinSizes[i] > basinSizes[j]
	})

	return basinSizes[0] * basinSizes[1] * basinSizes[2]
}
