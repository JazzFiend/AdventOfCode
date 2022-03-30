require_relative '../../lib/instruction_decoder/navigation_instruction_decoder'

require_relative '../../lib/navigation_command/traditional_navigation/north_command'
require_relative '../../lib/navigation_command/traditional_navigation/south_command'
require_relative '../../lib/navigation_command/traditional_navigation/east_command'
require_relative '../../lib/navigation_command/traditional_navigation/west_command'
require_relative '../../lib/navigation_command/traditional_navigation/left_command'
require_relative '../../lib/navigation_command/traditional_navigation/right_command'
require_relative '../../lib/navigation_command/traditional_navigation/forward_command'

# rubocop:disable Metrics/BlockLength
RSpec.describe NavigationInstructionDecoder, '#instructions' do
  it 'returns an empty list given no instructions' do
    expect(NavigationInstructionDecoder.decode_instructions([])).to eq []
  end

  context 'Instruction Types' do
    it 'North action' do
      expect(NavigationInstructionDecoder.decode_instructions(['N5'])).to eq [NorthCommand.new(5)]
    end

    it 'South action' do
      expect(NavigationInstructionDecoder.decode_instructions(['S7'])).to eq [SouthCommand.new(7)]
    end

    it 'East action' do
      expect(NavigationInstructionDecoder.decode_instructions(['E1'])).to eq [EastCommand.new(1)]
    end

    it 'West action' do
      expect(NavigationInstructionDecoder.decode_instructions(['W11'])).to eq [WestCommand.new(11)]
    end

    it 'Left action' do
      expect(NavigationInstructionDecoder.decode_instructions(['L180'])).to eq [LeftCommand.new(180)]
    end

    it 'Right action' do
      expect(NavigationInstructionDecoder.decode_instructions(['R90'])).to eq [RightCommand.new(90)]
    end

    it 'Forward action' do
      expect(NavigationInstructionDecoder.decode_instructions(['F8'])).to eq [ForwardCommand.new(8)]
    end
  end

  it 'Multiple Instructions' do
    instructions = %w[L90 S3]
    expected = [
      LeftCommand.new(90),
      SouthCommand.new(3)
    ]

    expect(NavigationInstructionDecoder.decode_instructions(instructions)).to eq expected
  end

  context 'Acceptance Test' do
    it 'real example' do
      instructions = %w[F10 N3 F7 R90 F11]
      expected = [
        ForwardCommand.new(10),
        NorthCommand.new(3),
        ForwardCommand.new(7),
        RightCommand.new(90),
        ForwardCommand.new(11)
      ]

      expect(NavigationInstructionDecoder.decode_instructions(instructions)).to eq expected
    end
  end
end
# rubocop:enable Metrics/BlockLength
