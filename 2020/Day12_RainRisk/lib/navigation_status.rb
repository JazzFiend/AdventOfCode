class NavigationStatus
  # Can this be reader?
  attr_accessor :location, :waypoint

  def initialize(location, waypoint)
    @location = location
    @waypoint = waypoint
  end

  def ==(other)
    return true if @location == other.location && @waypoint == other.waypoint

    false
  end
end
