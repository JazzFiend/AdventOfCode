require_relative './instruction'

class NopInstruction < Instruction
  def to_string
    format('nop %s', @argument)
  end

  def execute(accumulator)
    accumulator
  end

  def update_program_counter(program_counter)
    program_counter + 1
  end

  def replaceable?
    true
  end

  def replace
    JmpInstruction.new(@argument.to_i)
  end
end
