package LanternfishSimulator

const FISH_MATING_TIME = 6
const YOUNG_FISH_MODIFIER = 2

type LanternfishSimulator interface {
	CountFish() int
	AdvanceDay()
}
