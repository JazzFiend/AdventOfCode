require_relative '../lib/waypoint_navigation'
require_relative '../lib/navigation_status'

require_relative '../lib/navigation_command/waypoint_navigation/north_waypoint_command'
require_relative '../lib/navigation_command/waypoint_navigation/south_waypoint_command'
require_relative '../lib/navigation_command/waypoint_navigation/west_waypoint_command'
require_relative '../lib/navigation_command/waypoint_navigation/east_waypoint_command'
require_relative '../lib/navigation_command/waypoint_navigation/left_waypoint_command'
require_relative '../lib/navigation_command/waypoint_navigation/right_waypoint_command'
require_relative '../lib/navigation_command/waypoint_navigation/forward_waypoint_command'

RSpec.describe WaypointNavigation, '#drive ship' do
  it 'empty instruction list should leave the ship at the origin and the waypoint at the default location' do
    verify_navigation_status([], NavigationStatus.new([0, 0], [10, 1]))
  end

  context 'Navigation Instructions' do
    it 'North' do
      verify_navigation_status([NorthWaypointCommand.new(8)], NavigationStatus.new([0, 0], [10, 9]))
    end

    it 'South' do
      verify_navigation_status([SouthWaypointCommand.new(2)], NavigationStatus.new([0, 0], [10, -1]))
    end

    it 'West' do
      verify_navigation_status([WestWaypointCommand.new(15)], NavigationStatus.new([0, 0], [-5, 1]))
    end

    it 'East' do
      verify_navigation_status([EastWaypointCommand.new(4)], NavigationStatus.new([0, 0], [14, 1]))
    end

    it 'Forward' do
      verify_navigation_status([ForwardWaypointCommand.new(50)], NavigationStatus.new([500, 50], [10, 1]))
    end
  end

  context 'Rotation instructions' do
    # TODO: May need more rotations than just these two.
    it 'Left 0' do
      verify_navigation_status([LeftWaypointCommand.new(0)], NavigationStatus.new([0, 0], [10, 1]))
    end

    it 'Left 90' do
      verify_navigation_status([LeftWaypointCommand.new(90)], NavigationStatus.new([0, 0], [-1, 10]))
    end

    it 'Left 180' do
      verify_navigation_status([LeftWaypointCommand.new(180)], NavigationStatus.new([0, 0], [-10, -1]))
    end

    it 'Left 270' do
      verify_navigation_status([LeftWaypointCommand.new(270)], NavigationStatus.new([0, 0], [1, -10]))
    end

    it 'Right 0' do
      verify_navigation_status([RightWaypointCommand.new(0)], NavigationStatus.new([0, 0], [10, 1]))
    end

    it 'Right 90' do
      verify_navigation_status([RightWaypointCommand.new(90)], NavigationStatus.new([0, 0], [1, -10]))
    end

    it 'Right 180' do
      verify_navigation_status([RightWaypointCommand.new(180)], NavigationStatus.new([0, 0], [-10, -1]))
    end

    it 'Right 270' do
      verify_navigation_status([RightWaypointCommand.new(270)], NavigationStatus.new([0, 0], [-1, 10]))
    end
  end

  context 'Acceptance Test' do
    it 'real example' do
      instructions = [
        ForwardWaypointCommand.new(10),
        NorthWaypointCommand.new(3),
        ForwardWaypointCommand.new(7),
        RightWaypointCommand.new(90),
        ForwardWaypointCommand.new(11)
      ]

      verify_navigation_status(instructions, NavigationStatus.new([214, -72], [4, -10]))
    end
  end
end

def verify_navigation_status(instructions, nav_status)
  ship = WaypointNavigation.new
  ship.navigate(instructions)
  expect(ship.waypoint_navigation).to eq nav_status
end
