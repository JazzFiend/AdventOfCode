require_relative './instruction_decoder'

require_relative '../navigation_command/traditional_navigation/right_command'
require_relative '../navigation_command/traditional_navigation/left_command'
require_relative '../navigation_command/traditional_navigation/forward_command'
require_relative '../navigation_command/traditional_navigation/north_command'
require_relative '../navigation_command/traditional_navigation/south_command'
require_relative '../navigation_command/traditional_navigation/east_command'
require_relative '../navigation_command/traditional_navigation/west_command'

# rubocop:disable Metrics/AbcSize, Metrics/CyclomaticComplexity, Metrics/MethodLength
class NavigationInstructionDecoder < InstructionDecoder
  def self.decode_instructions(instructions)
    decoded_instructions = []

    instructions.each do |instruction|
      value = instruction[1..-1].to_i

      case instruction[0]
      when 'N'
        decoded_instructions.append(NorthCommand.new(value))
      when 'S'
        decoded_instructions.append(SouthCommand.new(value))
      when 'E'
        decoded_instructions.append(EastCommand.new(value))
      when 'W'
        decoded_instructions.append(WestCommand.new(value))
      when 'L'
        decoded_instructions.append(LeftCommand.new(value))
      when 'R'
        decoded_instructions.append(RightCommand.new(value))
      when 'F'
        decoded_instructions.append(ForwardCommand.new(value))
      end
    end

    decoded_instructions
  end
end
# rubocop:enable Metrics/AbcSize, Metrics/CyclomaticComplexity, Metrics/MethodLength
