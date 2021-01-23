require_relative './instruction/nop_instruction'
require_relative './instruction/acc_instruction'
require_relative './instruction/jmp_instruction'

class InstructionDecoder
  def self.decode(instruction)
    opcode = extract_opcode(instruction)
    argument = extract_argument(instruction)

    raise 'Bad Instruction Seen' unless valid_opcode?(opcode)

    make_instruction(opcode, argument)
  end

  def self.extract_opcode(instruction_string)
    instruction_string.split(' ')[0]
  end

  def self.extract_argument(instruction_string)
    instruction_string.split(' ')[1]
  end

  def self.valid_opcode?(opcode)
    %w[nop acc jmp].include?(opcode)
  end

  def self.make_instruction(opcode, argument)
    case opcode
    when 'nop'
      NopInstruction.new(argument.to_i)
    when 'acc'
      AccInstruction.new(argument.to_i)
    when 'jmp'
      JmpInstruction.new(argument.to_i)
    end
  end
end
