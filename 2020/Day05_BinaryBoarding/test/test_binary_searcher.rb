require_relative '../lib/binary_searcher'

require 'minitest/autorun'

class TestBinarySearcher < Minitest::Test
  def test_single_character_search
    search_string = 'u'
    assert_equal(1, BinarySearcher.run_binary_search(search_string, 'l', 'u'))
  end

  def test_invalid_character
    search_string = 'r'
    assert_raises RuntimeError do
      BinarySearcher.run_binary_search(search_string, 'l', 'u')
    end
  end

  def test_two_character_search
    assert_equal(0, BinarySearcher.run_binary_search('ll', 'l', 'u'))
    assert_equal(1, BinarySearcher.run_binary_search('lu', 'l', 'u'))
    assert_equal(2, BinarySearcher.run_binary_search('ul', 'l', 'u'))
    assert_equal(3, BinarySearcher.run_binary_search('uu', 'l', 'u'))
  end

  def test_bunch_of_cases
    assert_equal(44, BinarySearcher.run_binary_search('FBFBBFF', 'F', 'B'))
    assert_equal(5, BinarySearcher.run_binary_search('RLR', 'L', 'R'))
    assert_equal(102, BinarySearcher.run_binary_search('BBFFBBF', 'F', 'B'))
    assert_equal(4, BinarySearcher.run_binary_search('RLL', 'L', 'R'))
    assert_equal(0, BinarySearcher.run_binary_search('FFFFFFF', 'F', 'B'))
    assert_equal(127, BinarySearcher.run_binary_search('BBBBBBB', 'F', 'B'))
  end
end
