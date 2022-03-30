require_relative '../navigation_command'

class ForwardCommand < NavigationCommand
  def execute(current_ship_location)
    case current_ship_location.facing
    when Facing::NORTH
      NorthCommand.new(@value).execute(current_ship_location)
    when Facing::WEST
      WestCommand.new(@value).execute(current_ship_location)
    when Facing::SOUTH
      SouthCommand.new(@value).execute(current_ship_location)
    when Facing::EAST
      EastCommand.new(@value).execute(current_ship_location)
    end
  end
end
