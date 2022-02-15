package GridConstructor

import (
	c "Day04_GiantSquid/Cell"
)

func ConstructGrid(gridNumber [][]int) [][]c.Cell {
	grid := [][]c.Cell{}
	for x := range gridNumber {
		line := []c.Cell{}
		for y := range gridNumber[x] {
			line = append(line, c.Cell{Value: gridNumber[x][y]})
		}
		grid = append(grid, line)
	}
	return grid
}
