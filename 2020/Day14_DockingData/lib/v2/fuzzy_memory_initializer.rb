require_relative '../memory_initializer'
require_relative 'instruction_decoder_v2'

class FuzzyMemoryInitializer < MemoryInitializer
  def initialize(instructions)
    super(instructions)
    @decoder = InstructionDecoderV2.new
  end

  private

  def create_initial_state
    MaskCommand.new(MemoryData.new([], {}), '000000000000000000000000000000000000').execute
  end
end
