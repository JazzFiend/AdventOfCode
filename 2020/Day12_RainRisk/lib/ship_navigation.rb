require_relative './facing'
require_relative './ship_location'

class ShipNavigation
  attr_reader :ship_location

  def initialize
    @ship_location = ShipLocation.new
    @ship_location.location = [0, 0]
    @ship_location.facing = Facing::EAST
  end

  def navigate(nav_instructions)
    nav_instructions.each do |instruction|
      @ship_location = instruction.execute(@ship_location)
    end
  end
end
