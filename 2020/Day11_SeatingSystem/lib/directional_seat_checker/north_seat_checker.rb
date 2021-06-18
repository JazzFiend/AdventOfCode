require_relative('directional_seat_checker')

class NorthSeatChecker < DirectionalSeatChecker
  private

  def initialize_x
    @i - 1
  end

  def initialize_y
    @j
  end

  def invalid_seat?(x, _y)
    x.negative?
  end

  def advance_x(x)
    x - 1
  end

  def advance_y(y)
    y
  end
end
