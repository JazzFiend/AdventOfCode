require_relative '../memory_initializer'
require_relative 'instruction_decoder_v1'

class StandardMemoryInitializer < MemoryInitializer
  def initialize(instructions)
    super(instructions)
    @decoder = InstructionDecoderV1.new
  end

  private

  def create_initial_state
    MaskCommand.new(MemoryData.new([], {}), 'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX').execute
  end
end
