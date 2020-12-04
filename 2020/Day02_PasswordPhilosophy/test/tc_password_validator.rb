require_relative '../src/password_rule_v1'
require_relative '../src/password_rule_v2'
require_relative '../src/password_validator'

require 'test/unit'

class TestPasswordValidator < Test::Unit::TestCase
  def test_correct_password_v1
    password_rule_string = '2-4 e: ncrenivrie'
    rule = PasswordRuleV1.new(password_rule_string)
    password = password_rule_string.split[2]
    assert_true(PasswordValidator.legal_password?(rule, password))
  end

  def test_incorrect_password_v1
    password_rule_string = '3-4 e: ncrenivrie'
    rule = PasswordRuleV1.new(password_rule_string)
    password = password_rule_string.split[2]
    assert_false(PasswordValidator.legal_password?(rule, password))
  end

  def test_correct_password_v2
    password_rule_string = '3-4 e: ncrenivrie'
    rule = PasswordRuleV2.new(password_rule_string)
    password = password_rule_string.split[2]
    assert_true(PasswordValidator.legal_password_v2?(rule, password))
  end

  def test_v2_too_many_occurances
    password_rule_string = '2-4 e: nerenivrie'
    rule = PasswordRuleV2.new(password_rule_string)
    password = password_rule_string.split[2]
    assert_false(PasswordValidator.legal_password_v2?(rule, password))
  end

  def test_v2_not_enough_occurances
    password_rule_string = '1-8 e: nerenivrie'
    rule = PasswordRuleV2.new(password_rule_string)
    password = password_rule_string.split[2]
    assert_false(PasswordValidator.legal_password_v2?(rule, password))
  end

  def test_puzzle_part_one
    password_database = File.read('./test/input.txt').split("\n")
    legal_passwords = 0
    password_database.each do |password_rule_string|
      rule = PasswordRuleV1.new(password_rule_string)
      password = password_rule_string.split[2]
      legal_passwords += 1 if PasswordValidator.legal_password?(rule, password)
    end
    assert_equal(378, legal_passwords)
  end

  def test_puzzle_part_two
    password_database = File.read('./test/input.txt').split("\n")
    legal_passwords = 0
    password_database.each do |password_rule_string|
      rule = PasswordRuleV2.new(password_rule_string)
      password = password_rule_string.split[2]
      legal_passwords += 1 if PasswordValidator.legal_password_v2?(rule, password)
    end
    assert_equal(280, legal_passwords)
  end
end
