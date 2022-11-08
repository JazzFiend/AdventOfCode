require_relative '../instruction_decoder'
require_relative 'memory_command_v1'

class InstructionDecoderV1 < InstructionDecoder
  def handle_memory_command(instruction, memory_data)
    memory_location = extract_location_parameter(instruction)
    memory_value_param = extract_value_parameter(instruction).to_i
    MemoryCommandV1.new(memory_data, memory_location, memory_value_param)
  end
end
