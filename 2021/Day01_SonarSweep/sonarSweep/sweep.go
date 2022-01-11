package sonarSweep

func countDepthIncreases(depthSlice []int) (total int) {
	for i := 1; i < len(depthSlice); i++ {
		if depthSlice[i-1] < depthSlice[i] {
			total++
		}
	}
	return
}

func countDepthIncreasesSlidingWindow(depthSlice []int) (total int) {
	for i := 3; i < len(depthSlice); i++ {
		previousWindow := depthSlice[i-3] + depthSlice[i-2] + depthSlice[i-1]
		currentWindow := depthSlice[i-2] + depthSlice[i-1] + depthSlice[i]
		if previousWindow < currentWindow {
			total++
		}
	}
	return
}
