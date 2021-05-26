require_relative '../lib/adapter_arrangement_counter'

require 'minitest/autorun'

class TestAdapterArrangementCounter < Minitest::Test
  def test_no_adapters
    assert_equal(1, AdapterArrangementCounter.count_arrangements([]))
  end

  def test_one_adapter
    assert_equal(1, AdapterArrangementCounter.count_arrangements([1]))
  end

  def test_two_adapters
    assert_equal(2, AdapterArrangementCounter.count_arrangements([1, 2]))
  end

  def test_two_non_sequential_adapters
    assert_equal(2, AdapterArrangementCounter.count_arrangements([1, 3]))
  end

  def test_three_adapters
    assert_equal(4, AdapterArrangementCounter.count_arrangements([1, 2, 3]))
  end

  def test_four_adapters
    assert_equal(7, AdapterArrangementCounter.count_arrangements([1, 2, 3, 4]))
  end

  def test_many_non_sequential
    assert_equal(3, AdapterArrangementCounter.count_arrangements([1, 2, 4, 7, 8]))
  end

  def test_unsorted
    assert_equal(3, AdapterArrangementCounter.count_arrangements([2, 1, 8, 7, 4]))
  end

  def test_example_one
    assert_equal(8, AdapterArrangementCounter.count_arrangements([16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4]))
  end

  def test_example_two
    assert_equal(19_208, AdapterArrangementCounter.count_arrangements([28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23,
                                                                       49, 45, 19, 38, 39, 11, 1, 32, 25, 35, 8, 17, 7,
                                                                       9, 4, 2, 34, 10, 3]))
  end

  def test_puzzle_two
    adapter_list = File.read('./test/input.txt').split("\n").map(&:to_i)
    assert_equal(24_179_327_893_504, AdapterArrangementCounter.count_arrangements(adapter_list))
  end
end
