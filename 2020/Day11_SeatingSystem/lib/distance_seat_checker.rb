require_relative './directional_seat_checker/north_west_seat_checker'
require_relative './directional_seat_checker/north_seat_checker'
require_relative './directional_seat_checker/north_east_seat_checker'
require_relative './directional_seat_checker/east_seat_checker'
require_relative './directional_seat_checker/south_east_seat_checker'
require_relative './directional_seat_checker/south_seat_checker'
require_relative './directional_seat_checker/south_west_seat_checker'
require_relative './directional_seat_checker/west_seat_checker'

class DistanceSeatChecker
  def self.all_seats_empty_in_all_directions?(i, j, seat_layout)
    directions = setup_direction_list(i, j, seat_layout)
    all_seats_empty = true
    directions.each do |direction|
      unless direction.first_seen_seat_is_empty?
        all_seats_empty = false
        break
      end
    end
    all_seats_empty
  end

  def self.too_crowded_at_a_distance?(i, j, seat_layout)
    directions = setup_direction_list(i, j, seat_layout)
    occupied_seat_count = 0
    directions.each do |direction|
      occupied_seat_count += 1 if direction.first_seen_seat_is_occupied?
    end
    occupied_seat_count >= 5
  end

  def self.setup_direction_list(i, j, seat_layout)
    [NorthWestSeatChecker.new(i, j, seat_layout), NorthSeatChecker.new(i, j, seat_layout),
     NorthEastSeatChecker.new(i, j, seat_layout), EastSeatChecker.new(i, j, seat_layout),
     SouthEastSeatChecker.new(i, j, seat_layout), SouthSeatChecker.new(i, j, seat_layout),
     SouthWestSeatChecker.new(i, j, seat_layout), WestSeatChecker.new(i, j, seat_layout)]
  end
end
