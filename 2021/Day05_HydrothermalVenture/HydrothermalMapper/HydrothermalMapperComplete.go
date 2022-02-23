package HydrothermalMapper

import co "Day05_HydrothermalVenture/CoordinateExtractor"

func MapVentsComplete(ventLines []string) [][]int {
	ventGrid := [][]int{{0}}
	for _, ventLine := range ventLines {
		lineCoordinate := co.ExtractStartAndFinish(ventLine)
		ventGrid = addVentToGrid(lineCoordinate, ventGrid)
	}
	return ventGrid
}

func CountOverlapsComplete(ventLines []string) int {
	ventGrid := MapVentsComplete(ventLines)
	return CalculateOverlap(ventGrid)
}

func addVentToGrid(lineCoordinate co.LineCoordinate, ventGrid [][]int) [][]int {
	if IsHorizontal(lineCoordinate) {
		lineCoordinate.StartX, lineCoordinate.FinishX = co.NormalizeStartAndFinish(lineCoordinate.StartX, lineCoordinate.FinishX)
		extendedVentGrid := ExtendGrid(lineCoordinate, ventGrid)
		return PopulateVentsHorizontally(lineCoordinate.StartX, lineCoordinate.FinishX, lineCoordinate.StartY, extendedVentGrid)
	} else if IsVertical(lineCoordinate) {
		lineCoordinate.StartY, lineCoordinate.FinishY = co.NormalizeStartAndFinish(lineCoordinate.StartY, lineCoordinate.FinishY)
		extendedVentGrid := ExtendGrid(lineCoordinate, ventGrid)
		return PopulateVentsVertically(lineCoordinate.StartY, lineCoordinate.FinishY, lineCoordinate.StartX, extendedVentGrid)
	} else {
		extendedVentGrid := ExtendGrid(lineCoordinate, ventGrid)
		return PopulateVentsDiagonally(lineCoordinate, extendedVentGrid)
	}
}

func PopulateVentsDiagonally(lineCoordinate co.LineCoordinate, ventGrid [][]int) [][]int {
	x := lineCoordinate.StartX
	y := lineCoordinate.StartY
	for y != lineCoordinate.FinishY {
		ventGrid[y][x]++
		if lineCoordinate.StartY < lineCoordinate.FinishY {
			y++
		} else {
			y--
		}
		if lineCoordinate.StartX < lineCoordinate.FinishX {
			x++
		} else {
			x--
		}
	}
	ventGrid[y][x]++
	return ventGrid
}
