class DirectionalSeatChecker
  def initialize(i, j, seat_layout)
    @i = i
    @j = j
    @seat_layout = seat_layout
  end

  def first_seen_seat_is_empty?
    find_first_seat != '#'
  end

  def first_seen_seat_is_occupied?
    find_first_seat == '#'
  end

  def find_first_seat
    x = initialize_x
    y = initialize_y
    first_seat = '.'
    until invalid_seat?(x, y)
      if @seat_layout[x][y] == '#'
        first_seat = '#'
        break
      end
      if @seat_layout[x][y] == 'L'
        first_seat = 'L'
        break
      end

      x = advance_x(x)
      y = advance_y(y)
    end
    first_seat
  end
end
