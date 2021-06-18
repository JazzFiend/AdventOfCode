require_relative 'seating_simulator'

class NaiveSeatingSimulator < SeatingSimulator
  private

  def compute_new_seat_value(i, j)
    return 'L' if @seat_layout[i][j] == '#' && too_crowded?(i, j)

    return '#' if @seat_layout[i][j] == 'L' && surrounding_seats_empty?(i, j)

    @seat_layout[i][j]
  end

  def too_crowded?(i, j)
    occupied_count = 0
    (-1..1).each do |x|
      (-1..1).each do |y|
        next if center_cell?(x, y) || out_of_bounds?(i + x, j + y, @seat_layout.length, @seat_layout[i].length)

        occupied_count += 1 if @seat_layout[i + x][j + y] == '#'
      end
    end
    occupied_count >= 4
  end

  def surrounding_seats_empty?(i, j)
    (-1..1).each do |x|
      (-1..1).each do |y|
        next if center_cell?(x, y) || out_of_bounds?(i + x, j + y, @seat_layout.length, @seat_layout[i].length)

        return false if @seat_layout[i + x][j + y] == '#'
      end
    end
    true
  end
end
