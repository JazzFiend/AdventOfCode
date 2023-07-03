package Set

type Set[T comparable] struct {
	setCollection map[T]int
}

func New[T comparable]() Set[T] {
	s := Set[T]{setCollection: make(map[T]int)}
	return s
}

func (s Set[T]) Add(value T) {
	s.setCollection[value] = 1
}

func (s Set[T]) Remove(value T) {
	delete(s.setCollection, value)
}

func (s Set[T]) Contains(value T) bool {
	_, ok := s.setCollection[value]
	return ok
}

func (s Set[T]) IsEmpty() bool {
	return len(s.setCollection) == 0
}

func (s Set[T]) GetSize() int {
	return len(s.setCollection)
}
