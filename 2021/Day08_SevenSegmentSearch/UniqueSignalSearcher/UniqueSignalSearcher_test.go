package UniqueSignalSearcher

import (
	"Day08_SevenSegmentSearch/Common/FileHelpers"
	a "Day08_SevenSegmentSearch/Common/TestAssertions"
	"testing"
)

func TestOneEmptyString(t *testing.T) {
	a.AssertIntEqual(FindSimpleSignalsInOutput([]string{""}), 0, t)
}

func TestNoUniqueValues(t *testing.T) {
	a.AssertIntEqual(FindSimpleSignalsInOutput([]string{"ab | a bcgfa cdfabg"}), 0, t)
}

func TestEachUniqueValue(t *testing.T) {
	testParameter := [][]string{
		{"b | ac"},
		{"c | fcd"},
		{"d | gbcd"},
		{"e | abcdefg"},
	}
	for _, signal := range testParameter {
		a.AssertIntEqual(FindSimpleSignalsInOutput(signal), 1, t)
	}
}

func TestMultipleOutputValues(t *testing.T) {
	a.AssertIntEqual(FindSimpleSignalsInOutput([]string{"dead beef decaf bad | a ab abc abcd abcde abcdef abcdefg"}), 4, t)
}

func TestManySignal(t *testing.T) {
	signals := []string{
		"ab | ac fcd",
		"cd | a edfac gfedcba",
	}
	a.AssertIntEqual(FindSimpleSignalsInOutput(signals), 3, t)
}

func TestAcceptance(t *testing.T) {
	signals := []string{
		"be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe",
		"edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc",
		"fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg",
		"fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb",
		"aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea",
		"fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb",
		"dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe",
		"bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef",
		"egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb",
		"gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce",
	}

	a.AssertIntEqual(FindSimpleSignalsInOutput(signals), 26, t)
}

func TestPartOne(t *testing.T) {
	signals, error := FileHelpers.ReadLines("../input.txt")
	if error != nil {
		panic("Problem with file input")
	}
	a.AssertIntEqual(FindSimpleSignalsInOutput(signals), 367, t)
}
