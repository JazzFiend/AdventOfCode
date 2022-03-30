require_relative '../lib/emergency_navigation_system'

RSpec.describe EmergencyNavigationSystem, '#condense emergency directions' do
  it 'Acceptance Test' do
    instructions = %w[F10 N3 F7 R90 F11]
    expect(EmergencyNavigationSystem.find_destination(instructions)).to eq [17, -8]
  end

  it 'Puzzle One' do
    instructions = File.read('./spec/input.txt').split
    expect(EmergencyNavigationSystem.find_destination(instructions)).to eq [700, -1256]
  end
end
