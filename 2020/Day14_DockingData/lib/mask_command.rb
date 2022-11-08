require_relative 'command'
require_relative 'memory_data'

class MaskCommand < Command
  def initialize(initial_state, mask_string)
    super()
    @initial_state = initial_state
    @mask_string = mask_string
  end

  def execute
    new_mask = @mask_string.scan(/\w/)
    MemoryData.new(new_mask, @initial_state.memory)
  end
end
