require_relative '../lib/boot_code_repair'
require_relative '../lib/instruction_decoder'

require 'minitest/autorun'

class TestBootCodeRepair < Minitest::Test
  def test_simple_loop
    instruction_strings = ['nop +0', 'jmp -1', 'acc +33']
    instructions = []
    instruction_strings.each do |string|
      instructions.append(InstructionDecoder.decode(string))
    end
    assert_equal(33, BootCodeRepair.repair_code(instructions))
  end

  def test_example
    instruction_text = File.read('./test/example.txt')
    instructions_tokenized = instruction_text.split("\n")
    instructions = []
    instructions_tokenized.each do |string|
      instructions.append(InstructionDecoder.decode(string))
    end
    assert_equal(8, BootCodeRepair.repair_code(instructions))
  end

  def test_problem_two
    instruction_text = File.read('./test/input.txt')
    instructions_tokenized = instruction_text.split("\n")
    instructions = []
    instructions_tokenized.each do |string|
      instructions.append(InstructionDecoder.decode(string))
    end
    assert_equal(797, BootCodeRepair.repair_code(instructions))
  end
end
