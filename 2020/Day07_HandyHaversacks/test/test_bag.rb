require_relative '../lib/bag'

require 'minitest/autorun'

class TestBagMapper < Minitest::Test
  def test_equal
    b1 = Bag.new('shiny', 'gold')
    b2 = Bag.new('shiny', 'gold')
    assert(b1.eql?(b2))
  end

  def test_not_equal
    b1 = Bag.new('shiny', 'gold')
    b2 = Bag.new('funky', 'fucia')
    assert(!b1.eql?(b2))
  end
end
