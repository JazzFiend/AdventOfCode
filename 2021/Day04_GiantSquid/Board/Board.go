package Board

import (
	c "Day04_GiantSquid/Cell"
	"fmt"
)

type Board struct{ Grid [][]c.Cell }

func (b *Board) GetGrid() [][]c.Cell {
	return b.Grid
}

func (b *Board) IsGameComplete() bool {
	return b.horizontalWin() || b.verticalWin()
}

func (b *Board) horizontalWin() bool {
	for y := range b.Grid {
		if b.isLineComplete(b.Grid[y]) {
			return true
		}
	}
	return false
}

func (b *Board) verticalWin() bool {
	for x := range b.Grid[0] {
		if b.isRowComplete(x) {
			return true
		}
	}
	return false
}

func (b *Board) isRowComplete(row int) bool {
	for y := range b.Grid {
		if !b.Grid[y][row].Marked {
			return false
		}
	}
	return true
}

func (b *Board) isLineComplete(line []c.Cell) bool {
	for x := range line {
		if !line[x].Marked {
			return false
		}
	}
	return true
}

func (b *Board) MarkCell(toMark int) {
	marked := false
	y := 0
	for !marked && y < len(b.Grid) {
		x := 0
		for !marked && x < len(b.Grid[y]) {
			if b.Grid[y][x].Value == toMark {
				b.Grid[y][x].Marked = true
				marked = true
			}
			x++
		}
		y++
	}

	if !marked {
		panic(fmt.Sprintf("Tried to mark a square that didn't exist: %d", toMark))
	}
}

func (b *Board) Contains(value int) bool {
	for y := range b.Grid {
		for x := range b.Grid[y] {
			if b.Grid[y][x].Value == value {
				return true
			}
		}
	}
	return false
}

func (b *Board) GetUnmarkedCells() []int {
	unmarked := []int{}
	for y := range b.Grid {
		for x := range b.Grid[y] {
			if !b.Grid[y][x].Marked {
				unmarked = append(unmarked, b.Grid[y][x].Value)
			}
		}
	}
	return unmarked
}
