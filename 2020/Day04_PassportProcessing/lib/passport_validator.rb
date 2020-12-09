class PassportValidator
  def self.north_pole_id_valid?(north_pole_id)
    validate_birth_year(north_pole_id['byr']) &&
      validate_issue_year(north_pole_id['iyr']) &&
      validate_expiration_year(north_pole_id['eyr']) &&
      validate_height(north_pole_id['hgt']) &&
      validate_hair_color(north_pole_id['hcl']) &&
      validate_eye_color(north_pole_id['ecl']) &&
      validate_passport_id(north_pole_id['pid'])
  end

  def self.validate_birth_year(year_string)
    year = year_string.to_i
    year >= 1920 && year <= 2002
  end

  def self.validate_issue_year(year_string)
    year = year_string.to_i
    year >= 2010 && year <= 2020
  end

  def self.validate_expiration_year(year_string)
    year = year_string.to_i
    year >= 2020 && year <= 2030
  end

  def self.validate_height(height)
    if height_in_cm?(height)
      validate_cm(height)
    elsif height_in_in?(height)
      validate_in(height)
    else
      false
    end
  end

  def self.height_in_cm?(height)
    height[height.length - 2] == 'c' && height[height.length - 1] == 'm'
  end

  def self.validate_cm(height)
    height_int = height[0..height.length - 2].to_i
    height_int >= 150 && height_int <= 193
  end

  def self.height_in_in?(height)
    height[height.length - 2] == 'i' && height[height.length - 1] == 'n'
  end

  def self.validate_in(height)
    height_int = height[0..height.length - 2].to_i
    height_int >= 59 && height_int <= 76
  end

  def self.validate_hair_color(color)
    return false unless color.length == 7
    return false if color[0] != '#'

    color_extract_hex = color[1..color.length - 1]
    color_extract_hex.split('').each do |char|
      return false unless %w[0 1 2 3 4 5 6 7 8 9 a b c d e f].include?(char)
    end
    true
  end

  def self.validate_eye_color(color)
    %w[amb blu brn gry grn hzl oth].include?(color)
  end

  def self.validate_passport_id(id)
    return false unless id.length == 9

    id_as_int = id.to_i
    id_as_int <= 999_999_999
  end
end
