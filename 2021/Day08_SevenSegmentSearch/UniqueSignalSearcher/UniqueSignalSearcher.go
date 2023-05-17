package UniqueSignalSearcher

import (
	coll "Day08_SevenSegmentSearch/Common/CollectionHelpers"
	"strings"
)

func FindSimpleSignalsInOutput(signalEntries []string) int {
	simpleSignalCount := 0
	for _, signalEntry := range signalEntries {
		if len(signalEntry) == 0 {
			continue
		}

		separatedOutput := extractOutput(signalEntry)
		for _, output := range separatedOutput {
			if isSimpleSignal(len(output)) {
				simpleSignalCount += 1
			}
		}
	}
	return simpleSignalCount
}

func extractOutput(signalEntry string) []string {
	separatedSignal := strings.Split(signalEntry, "|")
	return strings.Split(separatedSignal[1], " ")
}

func isSimpleSignal(signal int) bool {
	return coll.Contains([]int{2, 3, 4, 7}, signal)
}
