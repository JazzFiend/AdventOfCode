require_relative '../lib/toboggan'
require_relative '../lib/toboggan_route'

require 'minitest/autorun'

class TestTobogganRoute < Minitest::Test
  def test_one_cell_map
    map_text = '.'
    toboggan = Toboggan.new(0, 1)
    toboggan_route = TobogganRoute.new(map_text, toboggan)
    toboggan_route.advance
    assert toboggan_route.trip_complete
  end

  def test_overflow_map
    map_text = "..\n.."
    toboggan = Toboggan.new(2, 1)
    toboggan_route = TobogganRoute.new(map_text, toboggan)
    toboggan_route.advance
    refute toboggan_route.trip_complete
    assert_equal(OrderedPair.new(0, 1), toboggan_route.location)
    toboggan_route.advance
    assert toboggan_route.trip_complete
  end

  def test_single_tree_count
    map_text = "..\n#."
    toboggan = Toboggan.new(2, 1)
    toboggan_route = TobogganRoute.new(map_text, toboggan)
    toboggan_route.advance
    refute toboggan_route.trip_complete
    assert_equal(OrderedPair.new(0, 1), toboggan_route.location)
    toboggan_route.advance
    assert toboggan_route.trip_complete
    assert_same(1, toboggan_route.trees_encountered)
  end

  def test_problem_example
    map_text = File.read('./test/example.txt')
    toboggan = Toboggan.new(3, 1)
    toboggan_route = TobogganRoute.new(map_text, toboggan)
    toboggan_route.advance until toboggan_route.trip_complete
    assert_same(7, toboggan_route.trees_encountered)
  end

  def test_puzzle_part_one
    map_text = File.read('./test/input.txt')
    toboggan = Toboggan.new(3, 1)
    toboggan_route = TobogganRoute.new(map_text, toboggan)
    toboggan_route.advance until toboggan_route.trip_complete
    assert_same(247, toboggan_route.trees_encountered)
  end

  def test_puzzle_part_two
    map_text = File.read('./test/input.txt')
    tree_muliplier_total = calculate_trees_in_slope(Toboggan.new(1, 1), map_text)
    tree_muliplier_total *= calculate_trees_in_slope(Toboggan.new(3, 1), map_text)
    tree_muliplier_total *= calculate_trees_in_slope(Toboggan.new(5, 1), map_text)
    tree_muliplier_total *= calculate_trees_in_slope(Toboggan.new(7, 1), map_text)
    tree_muliplier_total *= calculate_trees_in_slope(Toboggan.new(1, 2), map_text)
    assert_same(2983070376, tree_muliplier_total)
  end

  def calculate_trees_in_slope(toboggan, map_text)
    toboggan_route = TobogganRoute.new(map_text, toboggan)
    toboggan_route.advance until toboggan_route.trip_complete
    toboggan_route.trees_encountered
  end
end
