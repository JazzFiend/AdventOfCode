require_relative './mask_command'

class InstructionDecoder
  def decode_instruction(memory_data, instruction)
    if instruction.include?('mask')
      handle_mask_commmand(instruction, memory_data)
    elsif instruction.include?('mem')
      handle_memory_command(instruction, memory_data)
    else
      raise 'Bad instruction seen'
    end
  end

  private

  def handle_mask_commmand(instruction, memory_data)
    mask_string = extract_value_parameter(instruction)
    MaskCommand.new(memory_data, mask_string)
  end

  def extract_value_parameter(instruction)
    instruction.split[2]
  end

  def extract_location_parameter(memory_instruction)
    mem_section = memory_instruction.split[0]
    matcher = mem_section.match(/\[([0-9]+)\]/)
    matcher[0][1...-1]
  end
end
