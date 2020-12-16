require_relative '../lib/plane_customs_calculator'

require 'minitest/autorun'

class TestPlaneCustomsCalculator < Minitest::Test
  def test_one_group_unique
    group_answers = "abcx\nabcy\nabcz"
    assert_equal(6, PlaneCustomsCalculator.calculate_unique_yes_count(group_answers))
  end

  def test_multiple_groups_unique
    group_answers = "abc\n\na\nb\nc\n\nab\nac\n\na\na\na\na\n\nb"
    assert_equal(11, PlaneCustomsCalculator.calculate_unique_yes_count(group_answers))
  end

  def test_puzzle_part_one
    group_answers = File.read('./test/input.txt')
    assert_equal(6583, PlaneCustomsCalculator.calculate_unique_yes_count(group_answers))
  end

  def test_one_group_collective
    group_answers = "abcx\nabcy\nabcz"
    assert_equal(3, PlaneCustomsCalculator.calculate_collective_yes_count(group_answers))
  end

  def test_multiple_groups_collective
    group_answers = "abc\n\na\nb\nc\n\nab\nac\n\na\na\na\na\n\nb"
    assert_equal(6, PlaneCustomsCalculator.calculate_collective_yes_count(group_answers))
  end

  def test_puzzle_part_two
    group_answers = File.read('./test/input.txt')
    assert_equal(3290, PlaneCustomsCalculator.calculate_collective_yes_count(group_answers))
  end
end
