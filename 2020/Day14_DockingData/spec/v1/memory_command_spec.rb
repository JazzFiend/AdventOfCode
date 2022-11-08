require_relative '../../lib/v1/memory_command_v1'
require_relative '../../lib/memory_data'
require_relative '../command_helper'

RSpec.describe MemoryCommandV1 do
  it 'Mask with all 1\'s' do
    initial_state = MemoryData.new(%w[1 1 1 1], {})
    mem = MemoryCommandV1.new(initial_state, 11, 0)
    result = mem.execute
    CommandHelper.assert_same_memory_data(result, MemoryData.new(%w[1 1 1 1], { 11 => 15 }))
  end

  it 'Mask with all 0\'s' do
    initial_state = MemoryData.new(%w[0 0 0 0], {})
    mem = MemoryCommandV1.new(initial_state, 7, 15)
    result = mem.execute
    CommandHelper.assert_same_memory_data(result, MemoryData.new(%w[0 0 0 0], { 7 => 0 }))
  end

  it 'Mask with all X\'s' do
    initial_state = MemoryData.new(%w[X X X X], {})
    mem = MemoryCommandV1.new(initial_state, 2, 10)
    result = mem.execute
    CommandHelper.assert_same_memory_data(result, MemoryData.new(%w[X X X X], { 2 => 10 }))
  end

  it 'add a value to memory that has existing items' do
    initial_state = MemoryData.new(%w[X X X X], { 2 => 4 })
    mem = MemoryCommandV1.new(initial_state, 4, 8)
    result = mem.execute
    CommandHelper.assert_same_memory_data(result, MemoryData.new(%w[X X X X], { 2 => 4, 4 => 8 }))
  end

  it 'Mask a value that is shorter length than the mask' do
    initial_state = MemoryData.new(%w[0 1 X], {})
    mem = MemoryCommandV1.new(initial_state, 18, 1)
    result = mem.execute
    CommandHelper.assert_same_memory_data(result, MemoryData.new(%w[0 1 X], { 18 => 3 }))
  end
end
