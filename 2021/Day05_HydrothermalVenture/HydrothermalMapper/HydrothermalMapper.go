package HydrothermalMapper

import "Day05_HydrothermalVenture/CoordinateExtractor"

func IsHorizontal(co CoordinateExtractor.LineCoordinate) bool {
	return co.StartY == co.FinishY
}

func IsVertical(co CoordinateExtractor.LineCoordinate) bool {
	return co.StartX == co.FinishX
}

func ExtendGrid(lineCoordinate CoordinateExtractor.LineCoordinate, ventGrid [][]int) [][]int {
	extendedVentGrid := extendGridHorizontally(ventGrid, lineCoordinate.StartX)
	extendedVentGrid = extendGridHorizontally(extendedVentGrid, lineCoordinate.FinishX)
	extendedVentGrid = extendGridVertically(extendedVentGrid, lineCoordinate.StartY)
	return extendGridVertically(extendedVentGrid, lineCoordinate.FinishY)
}

func extendGridHorizontally(ventGrid [][]int, finish int) [][]int {
	for i := 0; i < len(ventGrid); i++ {
		for j := len(ventGrid[i]) - 1; j < finish; j++ {
			ventGrid[i] = append(ventGrid[i], 0)
		}
	}
	return ventGrid
}

func extendGridVertically(ventGrid [][]int, finish int) [][]int {
	for i := len(ventGrid) - 1; i < finish; i++ {
		ventGrid = append(ventGrid, []int{0})
		for j := 1; j < len(ventGrid[0]); j++ {
			ventGrid[i+1] = append(ventGrid[i+1], 0)
		}
	}
	return ventGrid
}

func PopulateVentsHorizontally(start int, finish int, row int, ventGrid [][]int) [][]int {
	for i := start; i <= finish; i++ {
		ventGrid[row][i]++
	}
	return ventGrid
}

func PopulateVentsVertically(start int, finish int, column int, ventGrid [][]int) [][]int {
	for i := start; i <= finish; i++ {
		ventGrid[i][column]++
	}
	return ventGrid
}

func CalculateOverlap(ventGrid [][]int) int {
	total := 0
	for i := 0; i < len(ventGrid); i++ {
		for j := 0; j < len(ventGrid[0]); j++ {
			if ventGrid[i][j] >= 2 {
				total++
			}
		}
	}
	return total
}
