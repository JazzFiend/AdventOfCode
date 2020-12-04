require_relative '../src/password_rule_v2'
require 'test/unit'

class TestPasswordRule < Test::Unit::TestCase
  def test_decode_password
    password_rule_string = '2-5 f: fnvirnierbe'
    rule = PasswordRuleV2.new(password_rule_string)

    assert_equal(rule.positions, [2, 5])
    assert_equal(rule.char, 'f')
  end
end
