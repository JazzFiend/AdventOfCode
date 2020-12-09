class PassportParser
  @valid_passport_fields = %w[ecl pid eyr hcl byr iyr cid hgt]

  def self.parse_passport(passport)
    passport_map = {}
    tokenized_passport = passport.split
    tokenized_passport.each do |passport_item|
      key_value_passport_item = passport_item.split(':')
      raise 'Bad passport field seen' unless valid_passport_field?(key_value_passport_item[0])

      passport_map[key_value_passport_item[0]] = key_value_passport_item[1]
    end
    passport_map
  end

  def self.valid_passport_field?(field)
    @valid_passport_fields.include?(field)
  end

  def self.valid_passport?(id)
    @valid_passport_fields.each do |valid_field|
      return false unless id.include?(valid_field)
    end
    true
  end

  def self.valid_north_pole_id?(id)
    valid_north_pole_id_fields = @valid_passport_fields.clone
    valid_north_pole_id_fields.delete('cid')
    valid_north_pole_id_fields.each do |valid_field|
      return false unless id.include?(valid_field)
    end
    true
  end
end
