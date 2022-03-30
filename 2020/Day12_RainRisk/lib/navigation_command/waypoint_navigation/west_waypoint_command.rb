require_relative 'waypoint_command'

class WestWaypointCommand < WaypointCommand
  def execute(current_nav_status)
    new_waypoint = [current_nav_status.waypoint[0] - @value, current_nav_status.waypoint[1]]
    NavigationStatus.new(current_nav_status.location, new_waypoint)
  end
end
