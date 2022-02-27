package Day06_Lanternfish

import (
	ls "Day06_Lanternfish/LanternfishSimulator"
	assert "Day06_Lanternfish/TestAssertions"
	"strings"
	"testing"
)

func TestEmptyFishListEfficient(t *testing.T) {
	initial := ls.ConstructLanternfishSimulatorEfficient([]int{})
	sim := ls.LanternfishSimulatorEfficient{FishQuantity: initial}
	assert.AssertIntEqual(sim.CountFish(), 0, t)
}

func TestIdenticalFishListShouldBeReturnedEfficient(t *testing.T) {
	initial := ls.ConstructLanternfishSimulatorEfficient([]int{0})
	sim := ls.LanternfishSimulatorEfficient{FishQuantity: initial}
	assert.AssertIntEqual(sim.CountFish(), 1, t)
}

func TestAdvanceOneDayNoNewFishEfficient(t *testing.T) {
	initial := ls.ConstructLanternfishSimulatorEfficient([]int{5})
	expected := []int{0, 0, 0, 0, 1, 0, 0, 0, 0}
	sim := ls.LanternfishSimulatorEfficient{FishQuantity: initial}
	sim.AdvanceDay()
	assert.AssertIntSliceEqual(sim.FishQuantity, expected, t)
	assert.AssertIntEqual(sim.CountFish(), 1, t)
}

func TestAdvanceOneDayNewFishEfficient(t *testing.T) {
	initial := ls.ConstructLanternfishSimulatorEfficient([]int{0})
	expected := []int{0, 0, 0, 0, 0, 0, 1, 0, 1}
	sim := ls.LanternfishSimulatorEfficient{FishQuantity: initial}
	sim.AdvanceDay()
	assert.AssertIntSliceEqual(sim.FishQuantity, expected, t)
	assert.AssertIntEqual(sim.CountFish(), 2, t)
}

func TestMultipleFishEfficient(t *testing.T) {
	initial := ls.ConstructLanternfishSimulatorEfficient([]int{0, 2})
	expected := []int{0, 1, 0, 0, 0, 0, 1, 0, 1}
	sim := ls.LanternfishSimulatorEfficient{FishQuantity: initial}
	sim.AdvanceDay()
	assert.AssertIntSliceEqual(sim.FishQuantity, expected, t)
	assert.AssertIntEqual(sim.CountFish(), 3, t)
}

func TestAcceptanceLong(t *testing.T) {
	initial := ls.ConstructLanternfishSimulatorEfficient([]int{3, 4, 3, 1, 2})
	sim := ls.LanternfishSimulatorEfficient{FishQuantity: initial}
	for i := 0; i < 256; i++ {
		sim.AdvanceDay()
	}
	assert.AssertIntEqual(sim.CountFish(), 26984457539, t)
}

func TestPartTwo(t *testing.T) {
	longFishString, _ := ReadLines("input.txt")
	fishStringSlice := strings.Split(longFishString[0], ",")
	fish := []int{}
	for _, fishString := range fishStringSlice {
		fish = append(fish, convertStringToInt(fishString))
	}
	initial := ls.ConstructLanternfishSimulatorEfficient(fish)
	sim := ls.LanternfishSimulatorEfficient{FishQuantity: initial}
	for i := 0; i < 256; i++ {
		sim.AdvanceDay()
	}
	assert.AssertIntEqual(sim.CountFish(), 1601616884019, t)
}
