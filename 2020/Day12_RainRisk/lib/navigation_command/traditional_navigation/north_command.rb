require_relative '../navigation_command'

class NorthCommand < NavigationCommand
  def execute(current_ship_location)
    next_ship_location = ShipLocation.new
    next_ship_location.location = [current_ship_location.location[0], current_ship_location.location[1] + @value]
    next_ship_location.facing = current_ship_location.facing
    next_ship_location
  end
end
