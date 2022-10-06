class ConsecutiveBusCalculator
  def self.calculate_earliest_consecutive(bus_ids, starting_time = 0)
    bus_id_hash = ConsecutiveBusCalculator.populate_bus_hash(bus_ids)
    bus_id_hash_normalized = ConsecutiveBusCalculator.normalize_bus_ids_by_largest(bus_id_hash)
    largest_value = bus_id_hash_normalized.keys.max
    time_counter = ConsecutiveBusCalculator.calculate_first_departure(starting_time, largest_value)

    loop do
      break if ConsecutiveBusCalculator.buses_leave_consecutively?(bus_id_hash_normalized, time_counter)

      time_counter += largest_value
    end
    ConsecutiveBusCalculator.calculate_start_time(bus_id_hash_normalized[bus_ids[0]], time_counter)
  end

  def self.populate_bus_hash(bus_ids)
    bus_id_offset_hash = {}
    bus_ids.each_index do |i|
      next if bus_ids[i] == 'x'

      bus_id_offset_hash[bus_ids[i]] = i
    end
    bus_id_offset_hash
  end

  def self.normalize_bus_ids_by_largest(bus_id_offset_hash)
    largest_value = bus_id_offset_hash.keys.max
    offset = bus_id_offset_hash[largest_value]
    bus_id_offset_hash.transform_values do |value|
      value - offset
    end
  end

  def self.calculate_first_departure(starting_time, largest_value)
    if (starting_time % largest_value).zero?
      starting_time
    else
      starting_time + (largest_value - (starting_time % largest_value))
    end
  end

  def self.buses_leave_consecutively?(bus_id_offset_hash, timestamp)
    bus_ids = bus_id_offset_hash.keys
    bus_ids.each do |bus_id|
      if (timestamp + bus_id_offset_hash[bus_id]).negative? ||
         !((timestamp + bus_id_offset_hash[bus_id]) % bus_id).zero?
        return false
      end
    end
    true
  end

  def self.calculate_start_time(first_bus_id, normalized_time)
    starting_modifier = first_bus_id
    normalized_time + starting_modifier
  end

  # This is a much faster way to solve the problem, but it requires that all the bus ids be co-prime.
  def self.chinese_remainder(bus_ids)
    bi = ConsecutiveBusCalculator.calculate_mod_values(bus_ids)
    bus_ids_normalized = bus_ids.filter { |element| element != 'x' }
    n = bus_ids_normalized.reduce(1) { |product, num| product * num }
    ni = bus_ids_normalized.map { |bus_id| n / bus_id }

    xi = []
    bus_ids_normalized.each_index do |i|
      xi.push(ConsecutiveBusCalculator.inverse_mod(bus_ids_normalized[i], ni[i]))
    end

    product = []
    bi.each_index do |i|
      product.push(bi[i] * ni[i] * xi[i])
    end

    sum = product.reduce(0) { |total, num| total + num }
    sum % n
  end

  def self.inverse_mod(mod, int)
    result = 1
    result += 1 while (int * result) % mod != 1
    result
  end

  def self.calculate_mod_values(bus_ids)
    bi = []
    bus_ids.each_index do |i|
      next if bus_ids[i] == 'x'

      if i.zero?
        bi.push(0)
      else
        # Can this be negative?
        bi.push(bus_ids[i] - i)
      end
    end
    bi
  end
end
