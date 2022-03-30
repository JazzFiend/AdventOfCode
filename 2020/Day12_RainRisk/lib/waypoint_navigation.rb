class WaypointNavigation
  attr_reader :waypoint_navigation

  def initialize
    @waypoint_navigation = NavigationStatus.new([0, 0], [10, 1])
  end

  # TODO: Duplicate Code?
  def navigate(nav_instructions)
    nav_instructions.each do |instruction|
      @waypoint_navigation = instruction.execute(@waypoint_navigation)
    end
  end
end
