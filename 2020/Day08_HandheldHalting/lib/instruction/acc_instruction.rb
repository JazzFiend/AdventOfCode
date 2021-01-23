require_relative './instruction'

class AccInstruction < Instruction
  def to_string
    format('acc %s', @argument)
  end

  def execute(accumulator)
    accumulator + @argument
  end

  def update_program_counter(program_counter)
    program_counter + 1
  end

  def replaceable?
    false
  end
end
