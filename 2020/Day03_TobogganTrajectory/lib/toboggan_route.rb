require_relative 'ordered_pair'

class TobogganRoute
  attr_reader :trip_complete, :location, :trees_encountered

  def initialize(map_text, toboggan)
    @map = parse_map_text(map_text)
    @toboggan = toboggan
    @location = OrderedPair.new(0, 0)
    @trip_complete = false
    @trees_encountered = 0
  end

  def advance
    advance_horizontal
    advance_vertical
    check_trip_complete
    @trees_encountered += 1 if !trip_complete && @map[@location.y][@location.x] == '#'
  end

  private

  def parse_map_text(map_text)
    map = []
    map_text_lines = map_text.split("\n")
    map_text_lines.each do |line|
      map.append([])
      cells = line.split('')
      cells.each do |cell|
        map[map.length - 1].append(cell)
      end
    end
    map
  end

  def advance_horizontal
    @location.x += @toboggan.horizontal_move
    @location.x = @location.x % @map[@location.y].length if @location.x > @map[@location.y].length - 1
  end

  def advance_vertical
    @location.y += @toboggan.vertical_move
  end

  def check_trip_complete
    @trip_complete = true if @location.y > @map.length - 1
  end
end
