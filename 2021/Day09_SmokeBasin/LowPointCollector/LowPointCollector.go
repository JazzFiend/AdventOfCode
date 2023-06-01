package LowPointCollector

func CollectLowPoints(heightMap [][]int) []int {
	lowPoints := []int{}
	if len(heightMap) > 0 {
		for x, heightLine := range heightMap {
			for y, height := range heightLine {
				if isHeightLowerThanWest(height, heightMap, x, y) &&
					isHeightLowerThanEast(height, heightMap, x, y) &&
					isHeightLowerThanNorth(height, heightMap, x, y) &&
					isHeightLowerThanSouth(height, heightMap, x, y) {
					lowPoints = append(lowPoints, height)
				}
			}
		}
	}
	return lowPoints
}

func isHeightLowerThanWest(height int, heightMap [][]int, x int, y int) bool {
	if y == 0 {
		return true
	} else {
		return (height < heightMap[x][y-1])
	}
}

func isHeightLowerThanEast(height int, heightMap [][]int, x int, y int) bool {
	if y == len(heightMap[x])-1 {
		return true
	} else {
		return (height < heightMap[x][y+1])
	}
}

func isHeightLowerThanNorth(height int, heightMap [][]int, x int, y int) bool {
	if x == 0 {
		return true
	} else {
		return (height < heightMap[x-1][y])
	}
}

func isHeightLowerThanSouth(height int, heightMap [][]int, x int, y int) bool {
	if x == len(heightMap)-1 {
		return true
	} else {
		return (height < heightMap[x+1][y])
	}
}
