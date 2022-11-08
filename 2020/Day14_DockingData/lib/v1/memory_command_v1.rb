require_relative '../memory_command'

class MemoryCommandV1 < MemoryCommand
  def execute
    memory_value_masked = apply_mask(@memory_data.mask, @memory_value_param)
    @memory_data.memory[@memory_location] = memory_value_masked
    MemoryData.new(@memory_data.mask, @memory_data.memory)
  end

  private

  def apply_mask(mask, memory_value_param)
    binary_memory = convert_to_binary_array(memory_value_param, mask.length)
    masked_value = ''
    binary_memory.each_index do |i|
      masked_value += case mask[i]
                      when 'X'
                        binary_memory[i]
                      else
                        mask[i]
                      end
    end
    masked_value.to_i(2)
  end
end
