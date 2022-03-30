require_relative 'waypoint_command'

class ForwardWaypointCommand < WaypointCommand
  def execute(current_nav_status)
    new_location= [
      current_nav_status.location[0] + (current_nav_status.waypoint[0] * @value),
      current_nav_status.location[1] + (current_nav_status.waypoint[1] * @value)
    ]
    NavigationStatus.new(new_location, current_nav_status.waypoint)
  end
end
