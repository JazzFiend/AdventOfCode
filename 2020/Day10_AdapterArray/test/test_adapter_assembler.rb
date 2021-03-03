require_relative '../lib/adapter_assembler'

require 'minitest/autorun'

class TestAdapterAssembler < Minitest::Test
  def setup
    @assembler = AdapterAssembler.new
  end

  def test_one_adapter
    setup
    @assembler.assemble_adapters([1])
    assert_equal(1, @assembler.calculate_answer)
  end

  def test_two_adapters
    setup
    @assembler.assemble_adapters([1, 2])
    assert_equal(2, @assembler.calculate_answer)
  end

  def test_two_adapters_unsorted
    setup

  end

  def test_example_one
    setup
    @assembler.assemble_adapters([16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4])
    assert_equal(35, @assembler.calculate_answer)
  end

  def test_example_two
    setup
    @assembler.assemble_adapters([28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49, 45, 19,
                                  38, 39, 11, 1, 32, 25, 35, 8, 17, 7, 9, 4, 2, 34, 10, 3])
    assert_equal(220, @assembler.calculate_answer)
  end

  def test_puzzle_one
    setup
    adapter_list = File.read('./test/input.txt').split("\n").map(&:to_i)
    @assembler.assemble_adapters(adapter_list)
    assert_equal(2, @assembler.calculate_answer)
  end
end
