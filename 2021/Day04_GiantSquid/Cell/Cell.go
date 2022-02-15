package Cell

import "fmt"

type Cell struct {
	Value  int
	Marked bool
}

func (c *Cell) String() string {
	return fmt.Sprintf("%d", c.Value)
}
