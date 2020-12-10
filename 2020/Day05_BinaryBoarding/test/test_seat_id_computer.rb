require_relative '../lib/seat_id_computer'

require 'minitest/autorun'

class TestSeatIdComputer < Minitest::Test
  def test_bunch_of_seat_ids
    assert_equal(357, SeatIdComputer.compute_seat_id('FBFBBFFRLR'))
    assert_equal(567, SeatIdComputer.compute_seat_id('BFFFBBFRRR'))
    assert_equal(119, SeatIdComputer.compute_seat_id('FFFBBBFRRR'))
    assert_equal(820, SeatIdComputer.compute_seat_id('BBFFBBFRLL'))
  end

  def test_puzzle_part_one
    passes = File.read('./test/input.txt').split("\n")
    highest_seat_id = -1
    passes.each do |pass|
      seat_id = SeatIdComputer.compute_seat_id(pass)
      highest_seat_id = seat_id if seat_id > highest_seat_id
    end
    assert_equal(996, highest_seat_id)
  end

  def test_puzzle_part_two
    passes = File.read('./test/input.txt').split("\n")
    seat_list = []
    passes.each do |pass|
      seat_list.append(SeatIdComputer.compute_seat_id(pass))
    end
    seat_list_sorted = seat_list.sort
    unused_seats = []
    for i in (0..1023)
      unused_seats.append(i) unless seat_list_sorted.include?(i)
    end
    assert unused_seats.include?(671)
  end
end
