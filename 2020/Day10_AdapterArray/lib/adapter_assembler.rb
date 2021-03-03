class AdapterAssembler
  def initialize
    reset
  end

  def reset
    @one_jolt_difference = 0
    @three_jolt_difference = 0
  end

  def assemble_adapters(adapter_list)
    reset
    final_adapter_list = finailize_adapter_list(adapter_list)
    for i in 1..final_adapter_list.length - 1
      case final_adapter_list[i] - final_adapter_list[i - 1]
      when 1
        @one_jolt_difference += 1
      when 3
        @three_jolt_difference += 1
      end
    end
  end

  def finailize_adapter_list(adapter_list)
    sorted_with_base = adapter_list.append(0).sort
    last_element = sorted_with_base[sorted_with_base.length - 1]
    sorted_with_base.append(last_element + 3)
  end

  def calculate_answer
    @one_jolt_difference * @three_jolt_difference
  end
end
