require_relative './instruction'

class JmpInstruction < Instruction
  def to_string
    format('jmp %s', @argument)
  end

  def execute(accumulator)
    accumulator
  end

  def update_program_counter(program_counter)
    program_counter + @argument
  end

  def replaceable?
    true
  end

  def replace
    NopInstruction.new(@argument.to_i)
  end
end
