package CollectionHelpers

func Contains(slice []int, val int) bool {
	for _, n := range slice {
		if n == val {
			return true
		}
	}
	return false
}

func RemoveElementFromSlice[T comparable](slice []T, elementToRemove T) []T {
	shortenedSlice := []T{}
	for _, element := range slice {
		if elementToRemove != element {
			shortenedSlice = append(shortenedSlice, element)
		}
	}
	return shortenedSlice
}
