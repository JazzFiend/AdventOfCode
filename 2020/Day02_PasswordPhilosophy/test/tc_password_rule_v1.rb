require_relative '../src/password_rule_v1'
require 'test/unit'

class TestPasswordRule < Test::Unit::TestCase
  def test_decode_password
    password_rule_string = '12-25 f: fnvirnierbe'
    rule = PasswordRuleV1.new(password_rule_string)

    assert_equal(rule.min_char, 12)
    assert_equal(rule.max_char, 25)
    assert_equal(rule.char, 'f')
  end
end
