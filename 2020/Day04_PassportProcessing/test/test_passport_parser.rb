require_relative '../lib/passport_parser'

require 'minitest/autorun'

class TestPassportParser < Minitest::Test
  def test_one_line_passport
    passport = 'ecl:brn'
    id_key_value = PassportParser.parse_passport(passport)
    assert_equal(id_key_value['ecl'], 'brn')
  end

  def test_invalid_passport_value
    passport = 'bad:doink'
    assert_raises RuntimeError do
      PassportParser.parse_passport(passport)
    end
  end

  def test_valid_passport_spaces
    passport = 'ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm'
    id_key_value = PassportParser.parse_passport(passport)
    assert PassportParser.valid_passport?(id_key_value)
    assert PassportParser.valid_north_pole_id?(id_key_value)
  end

  def test_valid_passport_crs
    passport = "ecl:gry\npid:860033327\neyr:2020\nhcl:#fffffd\nbyr:1937\niyr:2017\ncid:147\nhgt:183cm"
    id_key_value = PassportParser.parse_passport(passport)
    assert PassportParser.valid_passport?(id_key_value)
    assert PassportParser.valid_north_pole_id?(id_key_value)
  end

  def test_example_file
    ids = extract_ids_from_text('./test/example.txt')
    polar_id_count = 0
    ids.each do |id|
      id_key_value = PassportParser.parse_passport(id)
      polar_id_count += 1 if PassportParser.valid_north_pole_id?(id_key_value)
    end
    assert_same(2, polar_id_count)
  end

  def test_puzzle_part_one
    ids = IdSeperator.seperate_ids_from_file('./test/input.txt')
    polar_id_count = 0
    ids.each do |id|
      id_key_value = PassportParser.parse_passport(id)
      polar_id_count += 1 if PassportParser.valid_north_pole_id?(id_key_value)
    end
    assert_same(230, polar_id_count)
  end

  def extract_ids_from_text(file_name)
    ids_text = File.read(file_name).split("\n")
    ids = []
    id_to_add = ''

    ids_text.each do |line|
      if line == ''
        ids.append(id_to_add)
        id_to_add = ''
      else
        id_to_add += ' '
        id_to_add += line
      end
    end
    ids.append(id_to_add)
    ids
  end
end
