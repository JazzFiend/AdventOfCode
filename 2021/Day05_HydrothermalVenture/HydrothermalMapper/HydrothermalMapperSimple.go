package HydrothermalMapper

import (
	co "Day05_HydrothermalVenture/CoordinateExtractor"
)

func MapVentsSimple(ventLines []string) [][]int {
	ventGrid := [][]int{{0}}
	for _, ventLine := range ventLines {
		lineCoordinate := co.ExtractStartAndFinish(ventLine)
		ventGrid = addVentToGridExecptDiagonal(lineCoordinate, ventGrid)
	}
	return ventGrid
}

func CountOverlapsSimple(ventLines []string) int {
	ventGrid := MapVentsSimple(ventLines)
	return CalculateOverlap(ventGrid)
}

func addVentToGridExecptDiagonal(lineCoordinate co.LineCoordinate, ventGrid [][]int) [][]int {
	if IsHorizontal(lineCoordinate) {
		lineCoordinate.StartX, lineCoordinate.FinishX = co.NormalizeStartAndFinish(lineCoordinate.StartX, lineCoordinate.FinishX)
		extendedVentGrid := ExtendGrid(lineCoordinate, ventGrid)
		return PopulateVentsHorizontally(lineCoordinate.StartX, lineCoordinate.FinishX, lineCoordinate.StartY, extendedVentGrid)
	} else if IsVertical(lineCoordinate) {
		lineCoordinate.StartY, lineCoordinate.FinishY = co.NormalizeStartAndFinish(lineCoordinate.StartY, lineCoordinate.FinishY)
		extendedVentGrid := ExtendGrid(lineCoordinate, ventGrid)
		return PopulateVentsVertically(lineCoordinate.StartY, lineCoordinate.FinishY, lineCoordinate.StartX, extendedVentGrid)
	}
	return ventGrid
}
