require_relative '../lib/emergency_waypoint_system'

RSpec.describe EmergencyWaypointSystem, '#condense emergency directions' do
  it 'Acceptance Test' do
    instructions = %w[F10 N3 F7 R90 F11]
    expect(EmergencyWaypointSystem.find_destination(instructions)).to eq [214, -72]
  end

  it 'Puzzle Two' do
    instructions = File.read('./spec/input.txt').split
    expect(EmergencyWaypointSystem.find_destination(instructions)).to eq [91_964, -34_833]
  end
end
