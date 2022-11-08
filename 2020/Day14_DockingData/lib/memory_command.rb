require_relative 'command'

class MemoryCommand < Command
  def initialize(memory_data, memory_location, memory_value_param)
    super()
    @memory_data = memory_data
    @memory_location = memory_location
    @memory_value_param = memory_value_param
  end

  private

  def convert_to_binary_array(number, desired_length)
    binary = number.to_s(2).split('')
    if binary.length < desired_length
      size_diff = desired_length - binary.length - 1
      (0..size_diff).each do
        binary.prepend('0')
      end
    end
    binary
  end
end
