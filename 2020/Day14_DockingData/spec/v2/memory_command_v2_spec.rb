require_relative '../../lib/v2/memory_command_v2'
require_relative '../../lib/memory_data'
require_relative '../command_helper'

RSpec.describe MemoryCommandV2 do
  it 'Mask with all 0\'s' do
    initial_state = MemoryData.new(%w[0 0 0 0], {})
    mem = MemoryCommandV2.new(initial_state, 7, 15)
    result = mem.execute
    CommandHelper.assert_same_memory_data(result, MemoryData.new(%w[0 0 0 0], { 7 => 15 }))
  end

  it 'Mask with all 1\'s' do
    initial_state = MemoryData.new(%w[1 1 1], {})
    mem = MemoryCommandV2.new(initial_state, 0, 67)
    result = mem.execute
    CommandHelper.assert_same_memory_data(result, MemoryData.new(%w[1 1 1], { 7 => 67 }))
  end

  it 'Mask with one X' do
    initial_state = MemoryData.new(%w[X], {})
    mem = MemoryCommandV2.new(initial_state, 0, 10)
    result = mem.execute
    CommandHelper.assert_same_memory_data(result, MemoryData.new(%w[X], { 0 => 10, 1 => 10 }))
  end

  it 'Mask with multiple X\'s' do
    initial_state = MemoryData.new(%w[X X], {})
    mem = MemoryCommandV2.new(initial_state, 2, 3)
    result = mem.execute
    CommandHelper.assert_same_memory_data(result, MemoryData.new(%w[X X], { 0 => 3, 1 => 3, 2 => 3, 3 => 3 }))
  end
end
