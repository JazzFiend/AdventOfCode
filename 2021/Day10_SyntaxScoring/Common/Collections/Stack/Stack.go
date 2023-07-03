package Stack

type Stack[T any] struct {
	stackData []T
}

func New[T any]() Stack[T] {
	newStack := Stack[T]{}
	return newStack
}

func NewWithPrefilled[T any](initialValues []T) Stack[T] {
	newStack := Stack[T]{}
	for _, value := range initialValues {
		newStack.Push(value)
	}
	return newStack
}

func (s *Stack[T]) Length() int {
	return len(s.stackData)
}

func (s *Stack[T]) Peek() (value T, ok bool) {
	if len(s.stackData) == 0 {
		var dummy T
		return dummy, false
	}
	return s.stackData[len(s.stackData)-1], true
}

func (s *Stack[T]) Push(value T) {
	s.stackData = append(s.stackData, value)
}

func (s *Stack[T]) Pop() T {
	lastElement := s.stackData[len(s.stackData)-1]
	s.stackData = s.removeLast(s.stackData)
	return lastElement
}

func (s *Stack[T]) removeLast(slice []T) []T {
	newSlice := []T{}
	for i := 0; i < len(s.stackData)-1; i++ {
		newSlice = append(newSlice, s.stackData[i])
	}
	return newSlice
}
