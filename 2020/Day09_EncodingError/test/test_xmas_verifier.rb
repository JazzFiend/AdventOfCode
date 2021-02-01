require_relative '../lib/xmas_verifier'

require 'minitest/autorun'

class TestXmasVerifier < Minitest::Test
  def test_preamble_size_2_one_verification
    sequence = [1, 2, 3]
    assert_raises RuntimeError do
      XmasVerifier.find_incorrect_xmas_value(sequence, 2)
    end
  end

  def test_preamble_size_2_one_verification_incorrect
    sequence = [1, 2, 10]
    assert_equal(10, XmasVerifier.find_incorrect_xmas_value(sequence, 2))
  end

  def test_preamble_size_2_multiple_verifications
    sequence = [1, 2, 3, 5, 7, 12]
    assert_equal(7, XmasVerifier.find_incorrect_xmas_value(sequence, 2))
  end

  def test_variable_preamble
    sequence = [1, 2, 3, 4, 5, 10]
    assert_equal(10, XmasVerifier.find_incorrect_xmas_value(sequence, 4))
  end

  def test_example
    sequence = [35, 20, 15, 25, 47, 40, 62, 55, 65, 95, 102, 117, 150, 182, 127, 219, 299, 277, 309, 576]
    assert_equal(127, XmasVerifier.find_incorrect_xmas_value(sequence, 5))
  end

  def test_part_one
    sequence_text = File.read('./test/input.txt')
    sequence_strings = sequence_text.split("\n")
    sequence = sequence_strings.map(&:to_i)
    assert_equal(69_316_178, XmasVerifier.find_incorrect_xmas_value(sequence, 25))
  end

  def test_short_weakness
    sequence = [1, 2, 3, 6]
    preamble = 3
    weakness = XmasVerifier.find_incorrect_xmas_value(sequence, preamble)
    assert_equal(4, XmasVerifier.exploit_weakness(sequence, weakness))
  end

  def test_example_two
    sequence = [35, 20, 15, 25, 47, 40, 62, 55, 65, 95, 102, 117, 150, 182, 127, 219, 299, 277, 309, 576]
    preamble = 5
    weakness = XmasVerifier.find_incorrect_xmas_value(sequence, preamble)
    assert_equal(62, XmasVerifier.exploit_weakness(sequence, weakness))
  end

  def test_part_two
    sequence_text = File.read('./test/input.txt')
    sequence_strings = sequence_text.split("\n")
    sequence = sequence_strings.map(&:to_i)
    weakness = XmasVerifier.find_incorrect_xmas_value(sequence, 25)
    assert_equal(9_351_526, XmasVerifier.exploit_weakness(sequence, weakness))
  end
end
