package Day04_GiantSquid

import (
	g "Day04_GiantSquid/BingoGame"
	b "Day04_GiantSquid/Board"
	gc "Day04_GiantSquid/GridConstructor"
	assert "Day04_GiantSquid/TestAssertions"
	"testing"
)

func TestNoBoards(t *testing.T) {
	bingoGame := g.BingoGame{}
	assert.AssertIntEqual(bingoGame.CalculateWinningScore(), 0, t)
}

func TestPlayOne2x2Board(t *testing.T) {
	initial := [][]int{{1, 2}, {3, 4}}
	board := b.Board{Grid: gc.ConstructGrid(initial)}
	bingoGame := g.BingoGame{Boards: []b.Board{board}}

	bingoGame.CallNumber(1)
	bingoGame.CallNumber(2)

	assert.AssertIntEqual(bingoGame.CalculateWinningScore(), 14, t)
}

func TestPlayMultiple2x2Boards(t *testing.T) {
	boards := []b.Board{}
	initial1 := [][]int{{1, 2}, {3, 4}}
	boards = append(boards, b.Board{Grid: gc.ConstructGrid(initial1)})
	initial2 := [][]int{{4, 8}, {12, 16}}
	boards = append(boards, b.Board{Grid: gc.ConstructGrid(initial2)})
	bingoGame := g.BingoGame{Boards: boards}

	bingoGame.CallNumber(4)
	bingoGame.CallNumber(2)
	bingoGame.CallNumber(3)
	bingoGame.CallNumber(12)

	assert.AssertIntEqual(bingoGame.CalculateWinningScore(), 288, t)
}

func TestNoGamesComplete(t *testing.T) {
	boards := []b.Board{}
	initial1 := [][]int{{1, 2}, {3, 4}}
	boards = append(boards, b.Board{Grid: gc.ConstructGrid(initial1)})
	initial2 := [][]int{{4, 8}, {12, 16}}
	boards = append(boards, b.Board{Grid: gc.ConstructGrid(initial2)})
	bingoGame := g.BingoGame{Boards: boards}

	assert.AssertFalse(bingoGame.AtLeastOneGameComplete(), t)
	assert.AssertFalse(bingoGame.AllGamesComplete(), t)
}

func TestAtLeastOneGameComplete(t *testing.T) {
	boards := []b.Board{}
	initial1 := [][]int{{1, 2}, {3, 4}}
	boards = append(boards, b.Board{Grid: gc.ConstructGrid(initial1)})
	initial2 := [][]int{{4, 8}, {12, 16}}
	boards = append(boards, b.Board{Grid: gc.ConstructGrid(initial2)})
	bingoGame := g.BingoGame{Boards: boards}

	bingoGame.CallNumber(1)
	bingoGame.CallNumber(2)
	assert.AssertTrue(bingoGame.AtLeastOneGameComplete(), t)
	assert.AssertFalse(bingoGame.AllGamesComplete(), t)
}

func TestAllGamesComplete(t *testing.T) {
	boards := []b.Board{}
	initial1 := [][]int{{1, 2}, {3, 4}}
	boards = append(boards, b.Board{Grid: gc.ConstructGrid(initial1)})
	initial2 := [][]int{{4, 8}, {12, 16}}
	boards = append(boards, b.Board{Grid: gc.ConstructGrid(initial2)})
	bingoGame := g.BingoGame{Boards: boards}

	bingoGame.CallNumber(1)
	bingoGame.CallNumber(2)
	bingoGame.CallNumber(4)
	bingoGame.CallNumber(8)
	assert.AssertTrue(bingoGame.AtLeastOneGameComplete(), t)
	assert.AssertTrue(bingoGame.AllGamesComplete(), t)
}

func TestGameAcceptanceFirstFinish(t *testing.T) {
	calledNumbers := readCalledNumbers(("acceptance.txt"))
	boards := readBoards("acceptance.txt")
	bingoGame := g.BingoGame{Boards: boards}
	for _, numberToCall := range calledNumbers {
		bingoGame.CallNumber(numberToCall)
		if bingoGame.AtLeastOneGameComplete() {
			break
		}
	}
	assert.AssertIntEqual(bingoGame.GetLastCompletedScore(), 4512, t)
}

func TestGameAcceptanceLastFinish(t *testing.T) {
	calledNumbers := readCalledNumbers(("acceptance.txt"))
	boards := readBoards("acceptance.txt")
	bingoGame := g.BingoGame{Boards: boards}
	for _, numberToCall := range calledNumbers {
		bingoGame.CallNumber(numberToCall)
		if bingoGame.AllGamesComplete() {
			break
		}
	}
	assert.AssertIntEqual(bingoGame.GetLastCompletedScore(), 1924, t)
}

func TestPuzzlePartOne(t *testing.T) {
	calledNumbers := readCalledNumbers(("input.txt"))
	boards := readBoards("input.txt")
	bingoGame := g.BingoGame{Boards: boards}
	for _, numberToCall := range calledNumbers {
		bingoGame.CallNumber(numberToCall)
		if bingoGame.AtLeastOneGameComplete() {
			break
		}
	}
	assert.AssertIntEqual(bingoGame.GetLastCompletedScore(), 87456, t)
}

func TestPuzzlePartTwo(t *testing.T) {
	calledNumbers := readCalledNumbers(("input.txt"))
	boards := readBoards("input.txt")
	bingoGame := g.BingoGame{Boards: boards}
	for _, numberToCall := range calledNumbers {
		bingoGame.CallNumber(numberToCall)
		if bingoGame.AllGamesComplete() {
			break
		}
	}
	assert.AssertIntEqual(bingoGame.GetLastCompletedScore(), 15561, t)
}
