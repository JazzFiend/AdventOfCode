package Day05_HydrothermalVenture

import (
	"Day05_HydrothermalVenture/HydrothermalMapper"
	assert "Day05_HydrothermalVenture/TestAssertions"
	"testing"
)

func TestEmptyVentList(t *testing.T) {
	assert.Assert2dIntSliceEqual(HydrothermalMapper.MapVentsSimple([]string{}), [][]int{{0}}, t)
}

func TestOnePointAtOrigin(t *testing.T) {
	assert.Assert2dIntSliceEqual(HydrothermalMapper.MapVentsSimple([]string{"0,0 -> 0,0"}), [][]int{{1}}, t)
}

func TestOneHorizontalLineAtOrigin(t *testing.T) {
	ventList := []string{"0,0 -> 1,0"}
	expected := [][]int{{1, 1}}
	assert.Assert2dIntSliceEqual(HydrothermalMapper.MapVentsSimple(ventList), expected, t)
}

func TestTwoHorizontalLinesDoNotExtend(t *testing.T) {
	ventList := []string{"0,0 -> 5,0", "2,0 -> 4,0"}
	expected := [][]int{{1, 1, 2, 2, 2, 1}}
	assert.Assert2dIntSliceEqual(HydrothermalMapper.MapVentsSimple(ventList), expected, t)
}

func TestDoNotStartAtOriginHorizontal(t *testing.T) {
	ventList := []string{"2,0 -> 5,0"}
	expected := [][]int{{0, 0, 1, 1, 1, 1}}
	assert.Assert2dIntSliceEqual(HydrothermalMapper.MapVentsSimple(ventList), expected, t)
}

func TestHorizontalLineRepresentedBackwards(t *testing.T) {
	ventList := []string{"6,0 -> 4,0"}
	expected := [][]int{{0, 0, 0, 0, 1, 1, 1}}
	assert.Assert2dIntSliceEqual(HydrothermalMapper.MapVentsSimple(ventList), expected, t)
}

func TestOneVerticalLineAtOrigin(t *testing.T) {
	ventList := []string{"0,0 -> 0,2"}
	expected := [][]int{{1}, {1}, {1}}
	assert.Assert2dIntSliceEqual(HydrothermalMapper.MapVentsSimple(ventList), expected, t)
}

func TestTwoVerticalLinesDoNotExtend(t *testing.T) {
	ventList := []string{"0,0 -> 0,3", "0,1 -> 0,2"}
	expected := [][]int{{1}, {2}, {2}, {1}}
	assert.Assert2dIntSliceEqual(HydrothermalMapper.MapVentsSimple(ventList), expected, t)
}

func TestDoNotStartAtOriginVertical(t *testing.T) {
	ventList := []string{"0,3 -> 0,6"}
	expected := [][]int{{0}, {0}, {0}, {1}, {1}, {1}, {1}}
	assert.Assert2dIntSliceEqual(HydrothermalMapper.MapVentsSimple(ventList), expected, t)
}

func TestVerticalLineRepresentedBackwards(t *testing.T) {
	ventList := []string{"0,1 -> 0,0"}
	expected := [][]int{{1}, {1}}
	assert.Assert2dIntSliceEqual(HydrothermalMapper.MapVentsSimple(ventList), expected, t)
}

func TestLinesInTheMiddle(t *testing.T) {
	ventList := []string{"2,1 -> 2,0", "1,1 -> 3,1"}
	expected := [][]int{{0, 0, 1, 0}, {0, 1, 2, 1}}
	assert.Assert2dIntSliceEqual(HydrothermalMapper.MapVentsSimple(ventList), expected, t)
	assert.AssertIntEqual(HydrothermalMapper.CountOverlapsSimple(ventList), 1, t)
}
