require_relative 'seating_simulator'
require_relative 'distance_seat_checker'

class DistanceSeatingSimulator < SeatingSimulator
  private

  def compute_new_seat_value(i, j)
    return 'L' if @seat_layout[i][j] == '#' && DistanceSeatChecker.too_crowded_at_a_distance?(i, j, @seat_layout)
    if @seat_layout[i][j] == 'L' && DistanceSeatChecker.all_seats_empty_in_all_directions?(i, j, @seat_layout)
      return '#'
    end

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
    occupied_count >= 5
  end
end
