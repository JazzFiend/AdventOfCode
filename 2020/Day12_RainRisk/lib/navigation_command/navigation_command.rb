class NavigationCommand
  attr_reader :value

  def initialize(value)
    @value = value
  end

  def ==(other)
    return true if self.class == other.class && @value == other.value

    false
  end
end
