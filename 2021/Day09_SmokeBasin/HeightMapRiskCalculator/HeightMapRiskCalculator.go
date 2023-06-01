package HeightMapRiskCalculator

import (
	"Day09_SmokeBasin/Common/Converters"
	"Day09_SmokeBasin/LowPointCollector"
)

func CalculateTotalRisk(heightMap []string) int {
	processedHeightMap := Converters.StringSliceTo2dIntSlice(heightMap)
	lowPoints := LowPointCollector.CollectLowPoints(processedHeightMap)
	return assessRisk(lowPoints)
}

func assessRisk(lowPoints []int) int {
	riskLevel := 0
	for _, lowPoint := range lowPoints {
		riskLevel += (lowPoint + 1)
	}
	return riskLevel
}
