class Bag
  attr_reader :adjective, :color

  def initialize(adjective, color)
    @adjective = adjective
    @color = color
  end

  def to_string
    "#{@adjective} #{@color}"
  end

  def eql?(other)
    @adjective == other.adjective && @color == other.color
  end

  def hash
    [@adjective, @color].hash
  end
end
