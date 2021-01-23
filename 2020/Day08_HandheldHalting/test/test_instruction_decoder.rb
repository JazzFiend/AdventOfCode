require_relative '../lib/instruction_decoder'

require 'minitest/autorun'

class TestInstructionDecoder < Minitest::Test
  def test_decode_nop
    instruction = InstructionDecoder.decode('nop +8')
    assert(instruction.eql?(NopInstruction.new(8)))
  end

  def test_decode_acc
    instruction = InstructionDecoder.decode('acc -2')
    assert(instruction.eql?(AccInstruction.new(-2)))
  end

  def test_decode_jmp
    instruction = InstructionDecoder.decode('jmp +5')
    assert(instruction.eql?(JmpInstruction.new(5)))
  end
end
