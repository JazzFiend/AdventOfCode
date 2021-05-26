class AdapterArrangementCounter
  def self.count_arrangements(adapter_list)
    return 1 if adapter_list.empty?

    bookended_adapter_list = AdapterArrangementCounter.bookend_adapters(adapter_list.sort)
    count_adapter_paths(bookended_adapter_list)
  end

  def self.bookend_adapters(adapter_list)
    new_array = [0]
    new_array.concat(adapter_list)
    new_array.append(adapter_list[adapter_list.length - 1] + 3)
    new_array
  end

  def self.count_adapter_paths(adapter_list)
    path_cache = []
    adapter_list.each_index do |i|
      if i.zero?
        path_cache.push(1)
        next
      end

      path_cache.push(calculate_paths_to_current_adapter(i, adapter_list, path_cache))
    end
    path_cache.last
  end

  def self.calculate_paths_to_current_adapter(index, adapter_list, path_cache)
    total_paths_to_current_adapter = 0
    [3, 2, 1].each do |negative_offset|
      if valid_connection?(index, negative_offset, adapter_list)
        total_paths_to_current_adapter += path_cache[index - negative_offset]
      end
    end
    total_paths_to_current_adapter
  end

  def self.valid_connection?(index, negative_offset, adapter_list)
    index - negative_offset >= 0 && adapter_list[index] - adapter_list[index - negative_offset] <= 3
  end
end
