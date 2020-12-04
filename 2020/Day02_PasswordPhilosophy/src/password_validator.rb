class PasswordValidator
  def self.legal_password?(rule, password)
    character_count = count_characters(password)
    !character_count[rule.char].nil? &&
      character_count[rule.char] >= rule.min_char &&
      character_count[rule.char] <= rule.max_char
  end

  def self.count_characters(password)
    character_count = {}
    password_char_count = password.split('')
    password_char_count.each do |char|
      character_count[char] = if character_count[char].nil?
                                1
                              else
                                character_count[char] + 1
                              end
    end
    character_count
  end

  def self.legal_password_v2?(rule, password)
    valid = false
    rule.positions.each do |position|
      return false if valid == true && password[position - 1] == rule.char

      valid = true if valid == false && password[position - 1] == rule.char
    end
    valid
  end
end
