require_relative '../lib/instruction/nop_instruction'
require_relative '../lib/instruction/acc_instruction'
require_relative '../lib/processor'
require_relative '../lib/instruction_decoder'

require 'minitest/autorun'

class TestProcessor < Minitest::Test
  def test_no_instructions
    instruction_list = []
    processor = Processor.new
    processor.run(instruction_list)
    assert(processor.accumulator.zero?)
  end

  def test_one_nop
    instruction_list = []
    instruction_list.append NopInstruction.new(-5)
    processor = Processor.new
    processor.run(instruction_list)
    assert(processor.accumulator.zero?)
  end

  def test_acc
    instruction_strings = ['acc +5', 'acc +2', 'acc -9']
    instructions = []
    instruction_strings.each do |string|
      instructions.append(InstructionDecoder.decode(string))
    end
    processor = Processor.new
    processor.run(instructions)
    assert(processor.accumulator == -2)
  end

  def test_jmp
    instruction_strings = ['acc +5', 'jmp +2', 'acc +2', 'acc -9']
    instructions = []
    instruction_strings.each do |string|
      instructions.append(InstructionDecoder.decode(string))
    end
    processor = Processor.new
    processor.run(instructions)
    assert(processor.accumulator == -4)
  end

  def test_infinite_loop
    instruction_strings = ['acc +5', 'acc +2', 'acc -9', 'jmp -3']
    instructions = []
    instruction_strings.each do |string|
      instructions.append(InstructionDecoder.decode(string))
    end
    processor = Processor.new
    processor.run(instructions)
    assert(processor.accumulator == -2)
  end

  def test_example
    instruction_text = File.read('./test/example.txt')
    instructions_tokenized = instruction_text.split("\n")
    instructions = []
    instructions_tokenized.each do |string|
      instructions.append(InstructionDecoder.decode(string))
    end
    processor = Processor.new
    processor.run(instructions)
    assert(processor.accumulator == 5)
  end

  def test_part_one
    instruction_text = File.read('./test/input.txt')
    instructions_tokenized = instruction_text.split("\n")
    instructions = []
    instructions_tokenized.each do |string|
      instructions.append(InstructionDecoder.decode(string))
    end
    processor = Processor.new
    processor.run(instructions)
    assert(processor.accumulator == 1782)
  end
end
