require_relative('directional_seat_checker')

class NorthWestSeatChecker < DirectionalSeatChecker
  private

  def initialize_x
    @i - 1
  end

  def initialize_y
    @j - 1
  end

  def invalid_seat?(x, y)
    x.negative? || y.negative?
  end

  def advance_x(x)
    x - 1
  end

  def advance_y(y)
    y - 1
  end
end
