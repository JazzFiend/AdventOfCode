require_relative '../memory_command'

class MemoryCommandV2 < MemoryCommand
  def execute
    memory_locations = apply_mask(@memory_data.mask, @memory_location.to_i)
    memory_locations.each do |location|
      @memory_data.memory[location] = @memory_value_param
    end
    MemoryData.new(@memory_data.mask, @memory_data.memory)
  end

  private

  def apply_mask(mask, memory_location)
    binary_memory = convert_to_binary_array(memory_location, mask.length)
    masked_value = ''
    binary_memory.each_index do |i|
      masked_value += case mask[i]
                      when '0'
                        binary_memory[i]
                      else
                        mask[i]
                      end
    end
    calculate_memory_locations(masked_value)
  end

  def calculate_memory_locations(masked_value)
    locations = ['']
    masked_value_iter = masked_value.split('')
    masked_value_iter.each_index do |i|
      if masked_value_iter[i] == 'X'
        new_location_array = []
        locations.each do |location|
          new_location_array.append("#{location}0")
          new_location_array.append("#{location}1")
        end
        locations = new_location_array
      else
        locations.map! { |location| location + masked_value_iter[i] }
      end
    end
    locations.map { |location| location.to_i(2) }
  end
end
