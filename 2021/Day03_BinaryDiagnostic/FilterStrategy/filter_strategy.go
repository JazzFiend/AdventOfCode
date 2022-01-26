package FilterStrategy

type FilterStrategy interface {
	Filter(report []string, bitPosition int) []string
}

func filterByBitAndPosition(byteList []string, bitValue string, position int) []string {
	filtered := []string{}
	for _, byteString := range byteList {
		if byteString[position:position+1] == bitValue {
			filtered = append(filtered, byteString)
		}
	}
	return filtered
}
