package BasinSizeCalculator

import (
	Set "Day09_SmokeBasin/Common/Collections/Set"
)

func CalculateBasinSizes(heightMap [][]int) []int {
	basinSizes := []int{}
	// This set is always passed by reference. Everything that gets added to it will be reflected in all recursive calls.
	processedCells := Set.New[Pair]()
	for Y := range heightMap {
		for X, height := range heightMap[Y] {
			if !isMaxHeight(height) {
				basinSize := checkNextBasinCell(heightMap, Pair{X: X, Y: Y}, processedCells)
				basinSizes = addBasinSizeToList(basinSize, basinSizes)
			}
		}
	}
	return basinSizes
}

func checkNextBasinCell(heightMap [][]int, cell Pair, processedCells Set.Set[Pair]) int {
	if isMaxHeight(heightMap[cell.Y][cell.X]) || processedCells.Contains(cell) {
		return 0
	} else {
		processedCells.Add(cell)
		cellsToCheck := addCellsToCheck(cell, heightMap)
		basinSizeRunningTotal := 1
		for _, nextCell := range cellsToCheck {
			basinSizeRunningTotal += checkNextBasinCell(heightMap, nextCell, processedCells)
		}
		return basinSizeRunningTotal
	}
}

func isMaxHeight(height int) bool {
	return height >= 9
}

func addBasinSizeToList(newBasinSize int, basinSizes []int) []int {
	if newBasinSize > 0 {
		basinSizes = append(basinSizes, newBasinSize)
	}
	return basinSizes
}

func addCellsToCheck(cell Pair, heightMap [][]int) []Pair {
	cellsToCheck := []Pair{}
	if cell.X-1 >= 0 {
		cellsToCheck = append(cellsToCheck, Pair{X: cell.X - 1, Y: cell.Y})
	}
	if cell.X+1 < len(heightMap[cell.Y]) {
		cellsToCheck = append(cellsToCheck, Pair{X: cell.X + 1, Y: cell.Y})
	}
	if cell.Y-1 >= 0 {
		cellsToCheck = append(cellsToCheck, Pair{X: cell.X, Y: cell.Y - 1})
	}
	if cell.Y+1 < len(heightMap) {
		cellsToCheck = append(cellsToCheck, Pair{X: cell.X, Y: cell.Y + 1})
	}
	return cellsToCheck
}

type Pair struct {
	X int
	Y int
}
