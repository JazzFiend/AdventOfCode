require_relative 'instruction_decoder'
require_relative 'memory_data'

class MemoryInitializer
  def initialize(instructions)
    @instructions = instructions
    @decoder = InstructionDecoder.new
  end

  def initialize_memory
    mem_data = create_initial_state
    @instructions.each do |instruction|
      command = @decoder.decode_instruction(mem_data, instruction)
      mem_data = command.execute
    end
    mem_data.memory.values.reduce(0) { |sum, num| sum + num }
  end
end
