require_relative '../lib/passport_parser'
require_relative '../lib/passport_validator'
require_relative '../lib/id_seperator'

require 'minitest/autorun'

class TestPassportValidator < Minitest::Test
  def test_valid_passports
    seperated_ids = IdSeperator.seperate_ids_from_file('./test/valid_passports.txt')
    seperated_ids.each do |id|
      id_key_value = PassportParser.parse_passport(id)
      assert PassportValidator.north_pole_id_valid?(id_key_value)
    end
  end

  def test_invalid_passports
    seperated_ids = IdSeperator.seperate_ids_from_file('./test/invalid_passports.txt')
    seperated_ids.each do |id|
      id_key_value = PassportParser.parse_passport(id)
      assert !PassportValidator.north_pole_id_valid?(id_key_value)
    end
  end

  def test_individual_validations
    assert PassportValidator.validate_birth_year('2002')
    assert !PassportValidator.validate_birth_year('2003')

    assert PassportValidator.validate_height('60in')
    assert PassportValidator.validate_height('190cm')
    assert !PassportValidator.validate_height('190in')
    assert !PassportValidator.validate_height('190')

    assert PassportValidator.validate_hair_color('#123abc')
    assert !PassportValidator.validate_hair_color('#123abz')
    assert !PassportValidator.validate_hair_color('#123ab')
    assert !PassportValidator.validate_hair_color('123abc')

    assert PassportValidator.validate_eye_color('brn')
    assert !PassportValidator.validate_eye_color('wat')

    assert PassportValidator.validate_passport_id('000000001')
    assert !PassportValidator.validate_passport_id('0123456789')
    assert !PassportValidator.validate_passport_id('1')
  end

  def test_puzzle_part_two
    ids = IdSeperator.seperate_ids_from_file('./test/input.txt')
    valid_ids = 0
    ids.each do |id|
      id_key_value = PassportParser.parse_passport(id)
      valid_ids += 1 if PassportParser.valid_north_pole_id?(id_key_value) &&
                        PassportValidator.north_pole_id_valid?(id_key_value)
    end
    assert_same(156, valid_ids)
  end
end
