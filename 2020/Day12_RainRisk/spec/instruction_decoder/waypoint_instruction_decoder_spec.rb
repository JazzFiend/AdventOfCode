require_relative '../../lib/instruction_decoder/waypoint_instruction_decoder'

require_relative '../../lib/navigation_command/waypoint_navigation/north_waypoint_command'
require_relative '../../lib/navigation_command/waypoint_navigation/south_waypoint_command'
require_relative '../../lib/navigation_command/waypoint_navigation/east_waypoint_command'
require_relative '../../lib/navigation_command/waypoint_navigation/west_waypoint_command'
require_relative '../../lib/navigation_command/waypoint_navigation/left_waypoint_command'
require_relative '../../lib/navigation_command/waypoint_navigation/right_waypoint_command'
require_relative '../../lib/navigation_command/waypoint_navigation/forward_waypoint_command'

# rubocop:disable Metrics/BlockLength
RSpec.describe WaypointInstructionDecoder, '#instructions' do
  it 'returns an empty list given no instructions' do
    expect(WaypointInstructionDecoder.decode_instructions([])).to eq []
  end

  context 'Instruction Types' do
    it 'North action' do
      expect(WaypointInstructionDecoder.decode_instructions(['N5'])).to eq [NorthWaypointCommand.new(5)]
    end

    it 'South action' do
      expect(WaypointInstructionDecoder.decode_instructions(['S7'])).to eq [SouthWaypointCommand.new(7)]
    end

    it 'East action' do
      expect(WaypointInstructionDecoder.decode_instructions(['E1'])).to eq [EastWaypointCommand.new(1)]
    end

    it 'West action' do
      expect(WaypointInstructionDecoder.decode_instructions(['W11'])).to eq [WestWaypointCommand.new(11)]
    end

    it 'Left action' do
      expect(WaypointInstructionDecoder.decode_instructions(['L180'])).to eq [LeftWaypointCommand.new(180)]
    end

    it 'Right action' do
      expect(WaypointInstructionDecoder.decode_instructions(['R90'])).to eq [RightWaypointCommand.new(90)]
    end

    it 'Forward action' do
      expect(WaypointInstructionDecoder.decode_instructions(['F8'])).to eq [ForwardWaypointCommand.new(8)]
    end
  end

  it 'Multiple Instructions' do
    instructions = %w[L90 S3]
    expected = [
      LeftWaypointCommand.new(90),
      SouthWaypointCommand.new(3)
    ]

    expect(WaypointInstructionDecoder.decode_instructions(instructions)).to eq expected
  end

  context 'Acceptance Test' do
    it 'real example' do
      instructions = %w[F10 N3 F7 R90 F11]
      expected = [
        ForwardWaypointCommand.new(10),
        NorthWaypointCommand.new(3),
        ForwardWaypointCommand.new(7),
        RightWaypointCommand.new(90),
        ForwardWaypointCommand.new(11)
      ]

      expect(WaypointInstructionDecoder.decode_instructions(instructions)).to eq expected
    end
  end
end
# rubocop:enable Metrics/BlockLength
