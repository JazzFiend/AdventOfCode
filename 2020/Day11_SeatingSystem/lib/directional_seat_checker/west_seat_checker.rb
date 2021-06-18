require_relative('directional_seat_checker')

class WestSeatChecker < DirectionalSeatChecker
  def initialize_x
    @i
  end

  def initialize_y
    @j - 1
  end

  def invalid_seat?(_x, y)
    y.negative?
  end

  def advance_x(x)
    x
  end

  def advance_y(y)
    y - 1
  end
end
