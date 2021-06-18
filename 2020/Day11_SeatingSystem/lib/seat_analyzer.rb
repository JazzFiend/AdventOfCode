class SeatAnalyzer
  def self.count_occupied_seats_at_stablization(seating_simulator)
    previous_seat_layout = ''
    while previous_seat_layout != seating_simulator.readable_seating_chart
      previous_seat_layout = seating_simulator.readable_seating_chart
      seating_simulator.advance_turn
    end
    seating_simulator.readable_seating_chart.count('#')
  end
end
