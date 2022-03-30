require_relative 'waypoint_command'

class RightWaypointCommand < WaypointCommand
  def execute(current_nav_status) 
    new_waypoint = []

    case @value
    when 0
      new_waypoint = current_nav_status.waypoint
    when 90
      new_waypoint = [current_nav_status.waypoint[1], -1 * current_nav_status.waypoint[0]]
    when 180
      new_waypoint = [-1 * current_nav_status.waypoint[0], -1 * current_nav_status.waypoint[1]]
    when 270
      new_waypoint = [-1 * current_nav_status.waypoint[1], current_nav_status.waypoint[0]]
    end
    NavigationStatus.new(current_nav_status.location, new_waypoint)
  end
end
