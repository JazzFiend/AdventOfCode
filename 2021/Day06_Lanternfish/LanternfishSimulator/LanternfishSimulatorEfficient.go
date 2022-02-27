package LanternfishSimulator

type LanternfishSimulatorEfficient struct{ FishQuantity []int }

func (s *LanternfishSimulatorEfficient) CountFish() int {
	total := 0
	for _, fishCount := range s.FishQuantity {
		total += fishCount
	}
	return total
}

func (s *LanternfishSimulatorEfficient) AdvanceDay() {
	toAdvance := 0
	for i := len(s.FishQuantity) - 1; i >= 0; i-- {
		temp := s.FishQuantity[i]
		s.FishQuantity[i] = toAdvance
		toAdvance = temp
	}
	s.FishQuantity[FISH_MATING_TIME+YOUNG_FISH_MODIFIER] = toAdvance
	s.FishQuantity[FISH_MATING_TIME] += toAdvance
}
