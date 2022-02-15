package BingoGame

import "Day04_GiantSquid/Board"

type BingoGame struct {
	Boards             []Board.Board
	lastCalled         int
	lastCompletedScore int
	atLeastOneComplete bool
	allGamesComplete   bool
}

func (b *BingoGame) CalculateWinningScore() int {
	unmarked := []int{}
	for _, board := range b.Boards {
		if board.IsGameComplete() {
			unmarked = board.GetUnmarkedCells()
		}
	}
	return sumListOfInts(unmarked) * b.lastCalled
}

func (b *BingoGame) CallNumber(number int) {
	b.lastCalled = number
	for _, board := range b.Boards {
		if board.Contains(number) && !board.IsGameComplete() {
			board.MarkCell(number)
			b.checkGameComplete(board)
			if board.IsGameComplete() {
				b.lastCompletedScore = b.calculateScore(board)
			}
		}
	}
	b.allGamesComplete = b.checkAllGamesComplete()
}

func (b *BingoGame) checkGameComplete(board Board.Board) {
	if !b.atLeastOneComplete && board.IsGameComplete() {
		b.atLeastOneComplete = true
	}
}

func (b *BingoGame) calculateScore(board Board.Board) int {
	return sumListOfInts(board.GetUnmarkedCells()) * b.lastCalled
}

func (b *BingoGame) checkAllGamesComplete() bool {
	for _, board := range b.Boards {
		if !board.IsGameComplete() {
			return false
		}
	}
	return true
}

func sumListOfInts(intList []int) int {
	sum := 0
	for _, n := range intList {
		sum += n
	}
	return sum
}

func (b *BingoGame) AtLeastOneGameComplete() bool {
	return b.atLeastOneComplete
}

func (b *BingoGame) AllGamesComplete() bool {
	return b.allGamesComplete
}

func (b *BingoGame) GetLastCompletedScore() int {
	return b.lastCompletedScore
}
