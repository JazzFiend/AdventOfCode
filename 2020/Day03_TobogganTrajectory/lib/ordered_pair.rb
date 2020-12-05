class OrderedPair
  attr_accessor :x, :y

  def initialize(x, y)
    @x = x
    @y = y
  end

  def ==(other)
    return true if other.x == @x && other.y == y

    false
  end
end
