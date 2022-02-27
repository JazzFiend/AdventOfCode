package LanternfishSimulator

type LanternfishSimulatorSimple struct{ FishList []int }

func (s *LanternfishSimulatorSimple) CountFish() int {
	return len(s.FishList)
}

func (s *LanternfishSimulatorSimple) AdvanceDay() {
	for i := range s.FishList {
		if s.FishList[i] > 0 {
			s.FishList[i]--
		} else {
			s.FishList[i] = FISH_MATING_TIME
			s.FishList = append(s.FishList, FISH_MATING_TIME+YOUNG_FISH_MODIFIER)
		}
	}
}
