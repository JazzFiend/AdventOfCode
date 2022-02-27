package LanternfishSimulator

func ConstructLanternfishSimulatorEfficient(fishList []int) []int {

	fishQuantity := []int{}
	for i := 0; i <= FISH_MATING_TIME+YOUNG_FISH_MODIFIER; i++ {
		fishQuantity = append(fishQuantity, 0)
	}

	for _, fish := range fishList {
		fishQuantity[fish]++
	}

	return fishQuantity
}
