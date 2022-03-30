require_relative 'waypoint_command'

class NorthWaypointCommand < WaypointCommand
  def execute(current_nav_status)
    new_waypoint = [current_nav_status.waypoint[0], current_nav_status.waypoint[1] + @value]
    NavigationStatus.new(current_nav_status.location, new_waypoint)
  end
end
