require_relative '../instruction_decoder'
require_relative 'memory_command_v2'

class InstructionDecoderV2 < InstructionDecoder
  def handle_memory_command(instruction, memory_data)
    memory_location = extract_location_parameter(instruction)
    memory_value_param = extract_value_parameter(instruction).to_i
    MemoryCommandV2.new(memory_data, memory_location, memory_value_param)
  end
end
