require_relative '../navigation_command'

class RightCommand < NavigationCommand
  def execute(current_ship_location) 
    next_ship_location = ShipLocation.new
    next_ship_location.location = current_ship_location.location
    next_ship_location.facing = (current_ship_location.facing + @value) % 360
    next_ship_location
  end
end
