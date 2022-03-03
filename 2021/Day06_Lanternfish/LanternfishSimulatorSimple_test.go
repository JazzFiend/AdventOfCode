package Day06_Lanternfish

import (
	ls "Day06_Lanternfish/LanternfishSimulator"
	assert "Day06_Lanternfish/TestAssertions"
	"bufio"
	"os"
	"strconv"
	"strings"
	"testing"
)

func TestEmptyFishListSimple(t *testing.T) {
	sim := ls.LanternfishSimulatorSimple{FishList: []int{}}
	assert.AssertIntEqual(sim.CountFish(), 0, t)
}

func TestIdenticalFishListShouldBeReturnedSimple(t *testing.T) {
	sim := ls.LanternfishSimulatorSimple{FishList: []int{0}}
	assert.AssertIntEqual(sim.CountFish(), 1, t)
}

func TestAdvanceOneDayNoNewFishSimple(t *testing.T) {
	sim := ls.LanternfishSimulatorSimple{FishList: []int{5}}
	sim.AdvanceDay()
	assert.AssertIntSliceEqual(sim.FishList, []int{4}, t)
	assert.AssertIntEqual(sim.CountFish(), 1, t)
}

func TestAdvanceOneDayNewFishSimple(t *testing.T) {
	sim := ls.LanternfishSimulatorSimple{FishList: []int{0}}
	sim.AdvanceDay()
	assert.AssertIntSliceEqual(sim.FishList, []int{6, 8}, t)
	assert.AssertIntEqual(sim.CountFish(), 2, t)
}

func TestMultipleFishSimple(t *testing.T) {
	sim := ls.LanternfishSimulatorSimple{FishList: []int{0, 2}}
	sim.AdvanceDay()
	assert.AssertIntSliceEqual(sim.FishList, []int{6, 1, 8}, t)
	assert.AssertIntEqual(sim.CountFish(), 3, t)
}

func TestAcceptanceShortSimple(t *testing.T) {
	sim := ls.LanternfishSimulatorSimple{FishList: []int{3, 4, 3, 1, 2}}
	for i := 0; i < 80; i++ {
		sim.AdvanceDay()
	}
	assert.AssertIntEqual(sim.CountFish(), 5934, t)
}

func TestPartOne(t *testing.T) {
	longFishString, _ := ReadLines("input.txt")
	fishStringSlice := strings.Split(longFishString[0], ",")
	fish := []int{}
	for _, fishString := range fishStringSlice {
		fish = append(fish, convertStringToInt(fishString))
	}
	sim := ls.LanternfishSimulatorSimple{FishList: fish}
	for i := 0; i < 80; i++ {
		sim.AdvanceDay()
	}
	assert.AssertIntEqual(sim.CountFish(), 352151, t)
}

func ReadLines(path string) ([]string, error) {
	file, err := os.Open(path)
	if err != nil {
		return nil, err
	}
	defer file.Close()

	var lines []string
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		lines = append(lines, scanner.Text())
	}
	return lines, scanner.Err()
}

func convertStringToInt(s string) int {
	n, err := strconv.Atoi(s)
	if err != nil {
		panic(err)
	}
	return n
}
