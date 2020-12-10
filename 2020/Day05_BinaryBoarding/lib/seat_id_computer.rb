require_relative './binary_searcher'

class SeatIdComputer
  def self.compute_seat_id(seat_string)
    row_string = seat_string[0..6]
    column_string = seat_string[7..9]
    row = BinarySearcher.run_binary_search(row_string, 'F', 'B')
    column = BinarySearcher.run_binary_search(column_string, 'L', 'R')
    8 * row + column
  end
end
