require_relative '../lib/rule_extractor'
require_relative '../lib/bag_mapper'

require 'minitest/autorun'

class TestBagMapper < Minitest::Test
  def test_no_recursion
    bag_rules_text = "bright white bags contain 1 shiny gold bag.\n" \
    "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.\n" \
    'swanky red bags contain 1 busted brown bag, 2 shiny gold bags.'
    bag_rules = []
    bag_rules_tokenized = bag_rules_text.split("\n")
    bag_rules_tokenized.each do |rule|
      bag_rules.append(RuleExtractor.extract_rules_from_text(rule))
    end
    assert_equal(2, BagMapper.count_bag_packing_methods(bag_rules, Bag.new('shiny', 'gold')))
  end

  def test_one_level_recursion
    bag_rules_text = "bright white bags contain 1 shiny gold bag.\n" \
    "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.\n" \
    'swanky red bags contain 1 busted brown bag, 2 bright white bags.'
    bag_rules = []
    bag_rules_tokenized = bag_rules_text.split("\n")
    bag_rules_tokenized.each do |rule|
      bag_rules.append(RuleExtractor.extract_rules_from_text(rule))
    end
    assert_equal(2, BagMapper.count_bag_packing_methods(bag_rules, Bag.new('shiny', 'gold')))
  end

  def test_example
    bag_rules_text = File.read('./test/example.txt')
    bag_rules = []
    bag_rules_tokenized = bag_rules_text.split("\n")
    bag_rules_tokenized.each do |rule|
      bag_rules.append(RuleExtractor.extract_rules_from_text(rule))
    end
    assert_equal(4, BagMapper.count_bag_packing_methods(bag_rules, Bag.new('shiny', 'gold')))
  end

  # def test_part_one
  #   bag_rules_text = File.read('./test/input.txt')
  #   bag_rules = []
  #   bag_rules_tokenized = bag_rules_text.split("\n")
  #   bag_rules_tokenized.each do |rule|
  #     bag_rules.append(RuleExtractor.extract_rules_from_text(rule))
  #   end
  #   assert_equal(164, BagMapper.count_bag_packing_methods(bag_rules, Bag.new('shiny', 'gold')))
  # end
end
