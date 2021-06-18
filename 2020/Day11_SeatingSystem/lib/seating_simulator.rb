class SeatingSimulator
  def initialize(seat_layout)
    @seat_layout = []
    seat_layout.each do |line|
      @seat_layout.push([])
      @seat_layout.last.concat(line.split(''))
    end
  end

  def advance_turn
    new_layout = deep_copy_2d_array(@seat_layout)
    @seat_layout.each_index do |i|
      @seat_layout[i].each_index do |j|
        new_layout[i][j] = compute_new_seat_value(i, j)
      end
    end

    @seat_layout = deep_copy_2d_array(new_layout)
  end

  def readable_seating_chart
    seating_string = ''
    @seat_layout.each_index do |i|
      @seat_layout[i].each do |char|
        seating_string += char
      end
      seating_string += '\n' if i < @seat_layout.length - 1
    end
    seating_string
  end

  private

  def deep_copy_2d_array(array)
    new_array = []
    array.each_index do |i|
      array[i].each_index do |j|
        new_array.push([]) if j.zero?
        new_array[i].push(array[i][j])
      end
    end
    new_array
  end

  def center_cell?(x, y)
    x.zero? && y.zero?
  end

  def out_of_bounds?(x, y, num_rows, num_columns)
    x.negative? || y.negative? || x >= num_rows || y >= num_columns
  end
end
