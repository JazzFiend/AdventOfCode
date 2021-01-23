class Instruction
  attr_reader :argument

  def initialize(argument)
    @argument = argument
  end

  def to_string
    raise 'ABSTRACT'
  end

  def eql?(other)
    to_string == other.to_string
  end

  def execute
    raise 'ABSTRACT'
  end

  def replaceable?
    raise 'ABSTRACT'
  end

  def replace
    raise 'ABSTRACT'
  end
end
