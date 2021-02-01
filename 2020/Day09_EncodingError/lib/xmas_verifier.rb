class XmasVerifier
  MAX_INT = (2**(0.size * 8 - 2) - 1)

  def self.find_incorrect_xmas_value(sequence, preamble_size)
    for i in preamble_size..sequence.length - 1 do
      return sequence[i] unless contains_match?(sequence, preamble_size, i)
    end
    raise 'No error found in cipher'
  end

  def self.contains_match?(sequence, preamble_size, desired_sum_location)
    for j in desired_sum_location - preamble_size..desired_sum_location - 2 do
      for k in j + 1..desired_sum_location - 1 do
        return true if sequence[j] + sequence[k] == sequence[desired_sum_location]
      end
    end
    false
  end

  def self.exploit_weakness(sequence, weakness)
    for i in 0..sequence.length - 1
      total = 0
      max = 0
      min = MAX_INT
      for j in i..sequence.length - 1
        max = update_max(max, sequence[j])
        min = update_min(min, sequence[j])
        total += sequence[j]
        return min + max if total == weakness
      end
    end
    raise 'No exploit found'
  end

  def self.update_max(max, value)
    return value if max < value

    max
  end

  def self.update_min(min, value)
    return value if min > value

    min
  end
end
