require_relative '../lib/seat_analyzer'
require_relative '../lib/distance_seating_simulator'

require 'minitest/autorun'

class TestDistanceSeatAnalyzerSimulator < Minitest::Test
  def test_no_seats
    seating_simulator = DistanceSeatingSimulator.new([''])
    assert_equal(0, SeatAnalyzer.count_occupied_seats_at_stablization(seating_simulator))
  end

  def test_one_vacant_seat
    seating_simulator = DistanceSeatingSimulator.new(['L'])
    assert_equal(1, SeatAnalyzer.count_occupied_seats_at_stablization(seating_simulator))
  end

  def test_acceptance
    seating_chart = File.read('./test/acceptance.txt').split
    seating_simulator = DistanceSeatingSimulator.new(seating_chart)
    assert_equal(26, SeatAnalyzer.count_occupied_seats_at_stablization(seating_simulator))
  end

  def test_puzzle_two
    seating_chart = File.read('./test/input.txt').split
    seating_simulator = DistanceSeatingSimulator.new(seating_chart)
    assert_equal(2176, SeatAnalyzer.count_occupied_seats_at_stablization(seating_simulator))
  end
end
