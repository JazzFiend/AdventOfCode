require_relative './instruction_decoder'

class WaypointInstructionDecoder < InstructionDecoder
  def self.decode_instructions(instructions)
    decoded_instructions = []

    instructions.each do |instruction|
      value = instruction[1..-1].to_i

      case instruction[0]
      when 'N'
        decoded_instructions.append(NorthWaypointCommand.new(value))
      when 'S'
        decoded_instructions.append(SouthWaypointCommand.new(value))
      when 'E'
        decoded_instructions.append(EastWaypointCommand.new(value))
      when 'W'
        decoded_instructions.append(WestWaypointCommand.new(value))
      when 'L'
        decoded_instructions.append(LeftWaypointCommand.new(value))
      when 'R'
        decoded_instructions.append(RightWaypointCommand.new(value))
      when 'F'
        decoded_instructions.append(ForwardWaypointCommand.new(value))
      end
    end

    decoded_instructions
  end
end
