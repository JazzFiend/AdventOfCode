require_relative '../../lib/v2/fuzzy_memory_initializer'

RSpec.describe FuzzyMemoryInitializer do
  it 'No instructions should give back empty memory' do
    instructions = []
    mem_init = FuzzyMemoryInitializer.new(instructions)
    expect(mem_init.initialize_memory).to eq 0
  end

  it 'Send one Memory Instruction - default passthrough mask is in effect' do
    instructions = ['mem[4] = 22']
    mem_init = FuzzyMemoryInitializer.new(instructions)
    expect(mem_init.initialize_memory).to eq 22
  end

  it 'Send two Memory Instructions - default passthrough mask' do
    instructions = ['mem[1] = 9', 'mem[54] = 100']
    mem_init = FuzzyMemoryInitializer.new(instructions)
    expect(mem_init.initialize_memory).to eq 109
  end

  it 'Send a memory instruction with a mask of all 1\'s' do
    instructions = ['mask = 111', 'mem[0] = 45']
    mem_init = FuzzyMemoryInitializer.new(instructions)
    expect(mem_init.initialize_memory).to eq 45
  end

  it 'Send a memory instruction with a mask of all 0\'s' do
    instructions = ['mask = 0000', 'mem[8] = 10']
    mem_init = FuzzyMemoryInitializer.new(instructions)
    expect(mem_init.initialize_memory).to eq 10
  end

  it 'Acceptance Test' do
    instructions = [
      'mask = 000000000000000000000000000000X1001X',
      'mem[42] = 100',
      'mask = 00000000000000000000000000000000X0XX',
      'mem[26] = 1'
    ]
    mem_init = FuzzyMemoryInitializer.new(instructions)
    expect(mem_init.initialize_memory).to eq 208
  end

  it 'Puzzle Two' do
    instructions = File.read('./spec/input.txt').split("\n")
    mem_init = FuzzyMemoryInitializer.new(instructions)
    expect(mem_init.initialize_memory).to eq 3_974_538_275_659
  end
end
