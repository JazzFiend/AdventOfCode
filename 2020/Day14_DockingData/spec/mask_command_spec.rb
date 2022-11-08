require_relative '../lib/mask_command'
require_relative '../lib/memory_data'
require_relative 'command_helper'

RSpec.describe MaskCommand do
  it 'should convert a mask string into a mask array' do
    initial_state = MemoryData.new([], {})
    command = MaskCommand.new(initial_state, '11XX00')
    result = command.execute
    CommandHelper.assert_same_memory_data(result, MemoryData.new(%w[1 1 X X 0 0], {}))
  end
end
