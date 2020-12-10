class BinarySearcher
  def self.run_binary_search(search_string, lower_character, upper_character)
    lower_limit = 0
    upper_limit = (2**search_string.length) - 1

    search_string.split('').each do |char|
      if char == lower_character
        upper_limit = ((upper_limit - lower_limit) / 2) + lower_limit
      elsif char == upper_character
        lower_limit = ((upper_limit - lower_limit) / 2) + lower_limit + 1
      else
        raise 'Incorrect value in search string'
      end
    end
    raise 'Search failed: %<lower_limit>d, %<upper_limit>d' unless upper_limit == lower_limit

    upper_limit
  end
end
