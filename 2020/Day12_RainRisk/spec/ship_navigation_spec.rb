require_relative '../lib/ship_navigation'

require_relative '../lib/navigation_command/traditional_navigation/north_command'
require_relative '../lib/navigation_command/traditional_navigation/south_command'
require_relative '../lib/navigation_command/traditional_navigation/west_command'
require_relative '../lib/navigation_command/traditional_navigation/east_command'
require_relative '../lib/navigation_command/traditional_navigation/left_command'
require_relative '../lib/navigation_command/traditional_navigation/right_command'
require_relative '../lib/navigation_command/traditional_navigation/forward_command'

RSpec.describe ShipNavigation, '#drive ship' do
  it 'empty instruction list should leave the ship at the origin' do
    verify_final_location([], [0, 0])
  end

  context 'Navigation Instructions' do
    it 'North' do
      verify_final_location([NorthCommand.new(8)], [0, 8])
    end

    it 'South' do
      verify_final_location([SouthCommand.new(2)], [0, -2])
    end

    it 'West' do
      verify_final_location([WestCommand.new(15)], [-15, 0])
    end

    it 'East' do
      verify_final_location([EastCommand.new(4)], [4, 0])
    end

    it 'Left' do
      verify_final_location([LeftCommand.new(90), ForwardCommand.new(21)], [0, 21])
    end

    it 'Right' do
      verify_final_location([RightCommand.new(180), ForwardCommand.new(1)], [-1, 0])
    end

    it 'Forward' do
      verify_final_location([ForwardCommand.new(50)], [50, 0])
    end
  end

  context 'Acceptance Test' do
    it 'real example' do
      instructions = [
        ForwardCommand.new(10),
        NorthCommand.new(3),
        ForwardCommand.new(7),
        RightCommand.new(90),
        ForwardCommand.new(11)
      ]

      verify_final_location(instructions, [17, -8])
    end
  end
end

def verify_final_location(instructions, destination)
  ship = ShipNavigation.new
  ship.navigate(instructions)
  expect(ship.ship_location.location).to eq destination
end
