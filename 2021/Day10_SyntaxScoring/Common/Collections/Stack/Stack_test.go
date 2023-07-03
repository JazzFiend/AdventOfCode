package Stack

// Not testing error cases here because laziness.

import (
	a "Day10_SyntaxScoring/Common/TestAssertions"
	"testing"
)

func TestEmptyStack(t *testing.T) {
	stack := New[int]()
	_, ok := stack.Peek()
	a.AssertFalse(ok, t)
	a.AssertEqual(stack.Length(), 0, t)
}

func TestPrefilledStack(t *testing.T) {
	stack := NewWithPrefilled[int]([]int{5, 13, 900})
	val, ok := stack.Peek()
	a.AssertTrue(ok, t)
	a.AssertEqual(val, 900, t)
	a.AssertEqual(stack.Length(), 3, t)
}

func TestAddValue(t *testing.T) {
	stack := New[int]()
	stack.Push(4)
	top, ok := stack.Peek()
	a.AssertTrue(ok, t)
	a.AssertEqual(top, 4, t)
	a.AssertEqual(stack.Length(), 1, t)
}

func TestAddMultipleValues(t *testing.T) {
	stack := New[string]()
	stack.Push("e")
	stack.Push("j")
	stack.Push("v")
	top, ok := stack.Peek()
	a.AssertTrue(ok, t)
	a.AssertEqual(top, "v", t)
	a.AssertEqual(stack.Length(), 3, t)
}

func TestRemoveValues(t *testing.T) {
	stack := New[int]()
	stack.Push(5)
	stack.Push(111)
	stack.Push(9)
	stack.Push(22)
	stack.Pop()
	stack.Pop()
	top, ok := stack.Peek()
	a.AssertTrue(ok, t)
	a.AssertEqual(top, 111, t)
	a.AssertEqual(stack.Length(), 2, t)
}
