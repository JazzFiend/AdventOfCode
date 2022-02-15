package Day04_GiantSquid

import (
	b "Day04_GiantSquid/Board"
	c "Day04_GiantSquid/Cell"
	gc "Day04_GiantSquid/GridConstructor"
	assert "Day04_GiantSquid/TestAssertions"
	"testing"
)

func TestCreate1x1Board(t *testing.T) {
	board := b.Board{Grid: gc.ConstructGrid([][]int{{1}})}
	expectedBoard := [][]c.Cell{{c.Cell{Value: 1, Marked: false}}}
	assert.AssertGridEquals(board.GetGrid(), expectedBoard, t)
	assert.AssertFalse(board.IsGameComplete(), t)
}

func TestWin1x1Board(t *testing.T) {
	board := b.Board{Grid: gc.ConstructGrid([][]int{{5}})}
	board.MarkCell(5)
	assert.AssertTrue(board.IsGameComplete(), t)
}

func TestContainsNumber(t *testing.T) {
	board := b.Board{Grid: gc.ConstructGrid([][]int{{8}})}
	assert.AssertTrue(board.Contains(8), t)
}

func TestDoesNotContainNumber(t *testing.T) {
	board := b.Board{Grid: gc.ConstructGrid([][]int{{2}})}
	assert.AssertFalse(board.Contains(8), t)
}

func TestCreate2x2Board(t *testing.T) {
	initial := [][]int{{1, 2}, {3, 4}}
	board := b.Board{Grid: gc.ConstructGrid(initial)}
	expectedBoard := [][]c.Cell{
		{c.Cell{Value: 1, Marked: false}, c.Cell{Value: 2, Marked: false}},
		{c.Cell{Value: 3, Marked: false}, c.Cell{Value: 4, Marked: false}},
	}
	assert.AssertGridEquals(board.GetGrid(), expectedBoard, t)
	assert.AssertFalse(board.IsGameComplete(), t)
}

func TestWin2x2BoardHorizontal(t *testing.T) {
	initial := [][]int{{1, 2}, {3, 4}}
	board := b.Board{Grid: gc.ConstructGrid(initial)}
	board.MarkCell(3)
	board.MarkCell(4)
	assert.AssertTrue(board.IsGameComplete(), t)
}

func TestWin2x2BoardVertical(t *testing.T) {
	initial := [][]int{{1, 2}, {3, 4}}
	board := b.Board{Grid: gc.ConstructGrid(initial)}
	board.MarkCell(2)
	board.MarkCell(4)
	assert.AssertTrue(board.IsGameComplete(), t)
}

func TestNoWin2x2BoardDiagonal(t *testing.T) {
	initial := [][]int{{1, 2}, {3, 4}}
	board := b.Board{Grid: gc.ConstructGrid(initial)}
	board.MarkCell(1)
	board.MarkCell(4)
	assert.AssertFalse(board.IsGameComplete(), t)
}

func TestContainsNumber2x2(t *testing.T) {
	initial := [][]int{{2, 4}, {6, 8}}
	board := b.Board{Grid: gc.ConstructGrid(initial)}
	assert.AssertTrue(board.Contains(8), t)
}

func TestDoesNotContainNumber2x2(t *testing.T) {
	initial := [][]int{{2, 4}, {6, 8}}
	board := b.Board{Grid: gc.ConstructGrid(initial)}
	assert.AssertFalse(board.Contains(30), t)
}

func TestGetUnmarkedCells(t *testing.T) {
	initial := [][]int{{2, 3}, {4, 5}}
	board := b.Board{Grid: gc.ConstructGrid(initial)}
	board.MarkCell(3)
	board.MarkCell(4)
	assert.AssertIntListDeep(board.GetUnmarkedCells(), []int{2, 5}, t)
}

func TestBoardAcceptance(t *testing.T) {
	initial := [][]int{
		{22, 13, 17, 11, 0},
		{8, 2, 23, 4, 24},
		{21, 9, 14, 16, 7},
		{6, 10, 3, 18, 5},
		{1, 12, 20, 15, 19},
	}
	board := b.Board{Grid: gc.ConstructGrid(initial)}
	numbers := []int{8, 2, 21, 5, 32, 6, 22, 1}

	for _, n := range numbers {
		if board.Contains(n) {
			board.MarkCell(n)
		}
	}
	assert.AssertTrue(board.IsGameComplete(), t)
}
