require_relative '../../lib/v1/standard_memory_initializer'

RSpec.describe StandardMemoryInitializer do
  it 'Empty set of instructions' do
    instructions = []
    mem_init = StandardMemoryInitializer.new(instructions)
    expect(mem_init.initialize_memory).to eq 0
  end

  it 'One memory load instruction, no mask given' do
    instructions = ['mem[10] = 15']
    mem_init = StandardMemoryInitializer.new(instructions)
    expect(mem_init.initialize_memory).to eq 15
  end

  it 'One mask instruction' do
    instructions = ['mask = 10X']
    mem_init = StandardMemoryInitializer.new(instructions)
    expect(mem_init.initialize_memory).to eq 0
  end

  it 'Define mask with zeroes. Then load one memory value' do
    instructions = ['mask = 000', 'mem[3] = 7']
    mem_init = StandardMemoryInitializer.new(instructions)
    expect(mem_init.initialize_memory).to eq 0
  end

  it 'Define mask with ones. Then load one memory value' do
    instructions = ['mask = 111', 'mem[3] = 5']
    mem_init = StandardMemoryInitializer.new(instructions)
    expect(mem_init.initialize_memory).to eq 7
  end

  it 'Load up multiple parts of memory' do
    instructions = ['mask = 01X', 'mem[10] = 4', 'mem[4] = 1']
    mem_init = StandardMemoryInitializer.new(instructions)
    expect(mem_init.initialize_memory).to eq 5
  end

  it 'Reload existing memory values' do
    instructions = ['mem[99] = 12358', 'mem[99] = 2']
    mem_init = StandardMemoryInitializer.new(instructions)
    expect(mem_init.initialize_memory).to eq 2
  end

  it 'Acceptance Test' do
    instructions = [
      'mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X',
      'mem[8] = 11',
      'mem[7] = 101',
      'mem[8] = 0'
    ]
    mem_init = StandardMemoryInitializer.new(instructions)
    expect(mem_init.initialize_memory).to eq 165
  end

  it 'Puzzle One' do
    instructions = File.read('./spec/input.txt').split("\n")
    mem_init = StandardMemoryInitializer.new(instructions)
    expect(mem_init.initialize_memory).to eq 10_717_676_595_607
  end
end
