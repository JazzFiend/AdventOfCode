package Set

import (
	a "Day10_SyntaxScoring/Common/TestAssertions"
	"testing"
)

func TestInitializeSet(t *testing.T) {
	s := New[string]()
	a.AssertTrue(s.IsEmpty(), t)
	a.AssertEqual(s.GetSize(), 0, t)
}

func TestAddOneItem(t *testing.T) {
	s := New[string]()
	s.Add("item")
	a.AssertFalse(s.IsEmpty(), t)
	a.AssertEqual(s.GetSize(), 1, t)
	a.AssertTrue(s.Contains("item"), t)
}

func TestAddOneItemManyTimes(t *testing.T) {
	s := New[string]()
	s.Add("item")
	s.Add("item")
	s.Add("item")
	s.Add("item")
	a.AssertFalse(s.IsEmpty(), t)
	a.AssertEqual(s.GetSize(), 1, t)
	a.AssertTrue(s.Contains("item"), t)
}

func TestAddManyItems(t *testing.T) {
	s := New[string]()
	s.Add("item1")
	s.Add("item2")
	s.Add("item3")
	s.Add("item4")
	a.AssertFalse(s.IsEmpty(), t)
	a.AssertEqual(s.GetSize(), 4, t)
	a.AssertTrue(s.Contains("item1"), t)
	a.AssertTrue(s.Contains("item2"), t)
	a.AssertTrue(s.Contains("item3"), t)
	a.AssertTrue(s.Contains("item4"), t)
}

func TestRemoveItem(t *testing.T) {
	s := New[string]()
	s.Add("item1")
	s.Remove("item1")
	a.AssertTrue(s.IsEmpty(), t)
	a.AssertEqual(s.GetSize(), 0, t)
	a.AssertFalse(s.Contains("item1"), t)
}

func TestRemoveItemNotInSet(t *testing.T) {
	s := New[string]()
	s.Add("item1")
	s.Remove("item2")
	a.AssertFalse(s.IsEmpty(), t)
	a.AssertEqual(s.GetSize(), 1, t)
	a.AssertTrue(s.Contains("item1"), t)
	a.AssertFalse(s.Contains("item2"), t)
}

func TestGenerics(t *testing.T) {
	s := New[int]()
	s.Add(5)
	s.Add(2)
	s.Remove(2)
	a.AssertFalse(s.IsEmpty(), t)
	a.AssertEqual(s.GetSize(), 1, t)
	a.AssertTrue(s.Contains(5), t)
	a.AssertFalse(s.Contains(2), t)
}
