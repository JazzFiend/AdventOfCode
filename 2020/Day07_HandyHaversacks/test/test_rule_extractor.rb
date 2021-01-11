require_relative '../lib/rule_extractor'

require 'minitest/autorun'

class TestRuleExtractor < Minitest::Test
  def test_one_rule_no_contained_bag
    rule_text = 'faded blue bags contain no other bags.'
    bag_rule = RuleExtractor.extract_rules_from_text(rule_text)
    assert bag_rule.bag_description == 'faded blue'
    assert bag_rule.contained_bags.empty?
  end

  def test_one_rule_one_contained_bag
    rule_text = 'bright white bags contain 1 shiny gold bag.'
    bag_rule = RuleExtractor.extract_rules_from_text(rule_text)
    assert bag_rule.bag_description == 'bright white'
    assert bag_rule.contained_bags['shiny gold'] == 1
  end

  def test_one_rule_many_contained_bags
    rule_text = 'drab bronze bags contain 5 plaid lavender bags, 1 muted yellow bag, 3 vibrant coral bags.'
    bag_rule = RuleExtractor.extract_rules_from_text(rule_text)
    assert bag_rule.bag_description == 'drab bronze'
    assert bag_rule.contained_bags['plaid lavender'] == 5
    assert bag_rule.contained_bags['muted yellow'] == 1
    assert bag_rule.contained_bags['vibrant coral'] == 3
  end
end
