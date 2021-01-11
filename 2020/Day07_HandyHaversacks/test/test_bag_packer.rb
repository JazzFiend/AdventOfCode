require_relative '../lib/rule_extractor'
require_relative '../lib/bag_packer'

require 'minitest/autorun'

class TestBagPacker < Minitest::Test
  def test_one_rule_no_bags
    bag_rules_text = 'shiny gold bags contain no other bags.'
    bag_rules = []
    bag_rules.append(RuleExtractor.extract_rules_from_text(bag_rules_text))
    assert_equal(0, BagPacker.count_number_bags_needed(bag_rules, Bag.new('shiny', 'gold')))
  end

  def test_one_rule_one_bag_type
    bag_rules_text = 'shiny gold bags contain 2 vibrant plum bags.'
    bag_rules = []
    bag_rules.append(RuleExtractor.extract_rules_from_text(bag_rules_text))
    assert_equal(2, BagPacker.count_number_bags_needed(bag_rules, Bag.new('shiny', 'gold')))
  end

  def test_one_rule_many_bag_types
    bag_rules_text = 'shiny gold bags contain 2 vibrant plum bags, 3 bright white bags.'
    bag_rules = []
    bag_rules.append(RuleExtractor.extract_rules_from_text(bag_rules_text))
    assert_equal(5, BagPacker.count_number_bags_needed(bag_rules, Bag.new('shiny', 'gold')))
  end

  def test_one_level_of_nesting
    bag_rules_text = "shiny gold bags contain 2 vibrant plum bags, 2 bright white bags.\n" \
                     "vibrant plum bags contain 3 dark olive bags.\n" \
                     "dark olive bags contain no other bags.\n" \
                     'bright white bags contain no other bags.'
    bag_rules = []
    bag_rules_tokenized = bag_rules_text.split("\n")
    bag_rules_tokenized.each do |rule|
      bag_rules.append(RuleExtractor.extract_rules_from_text(rule))
    end
    assert_equal(10, BagPacker.count_number_bags_needed(bag_rules, Bag.new('shiny', 'gold')))
  end

  def test_many_levels_of_nesting
    bag_rules_text = "shiny gold bags contain 2 vibrant plum bags.\n" \
                     "vibrant plum bags contain 3 dark olive bags.\n" \
                     "dark olive bags contain 5 potato bacon bags.\n" \
                     'potato bacon bags contain no other bags.'
    bag_rules = []
    bag_rules_tokenized = bag_rules_text.split("\n")
    bag_rules_tokenized.each do |rule|
      bag_rules.append(RuleExtractor.extract_rules_from_text(rule))
    end
    assert_equal(38, BagPacker.count_number_bags_needed(bag_rules, Bag.new('shiny', 'gold')))
  end

  def test_example
    bag_rules_text = File.read('./test/example.txt')
    bag_rules = []
    bag_rules_tokenized = bag_rules_text.split("\n")
    bag_rules_tokenized.each do |rule|
      bag_rules.append(RuleExtractor.extract_rules_from_text(rule))
    end
    assert_equal(32, BagPacker.count_number_bags_needed(bag_rules, Bag.new('shiny', 'gold')))
  end

  def test_example_two
    bag_rules_text = File.read('./test/example2.txt')
    bag_rules = []
    bag_rules_tokenized = bag_rules_text.split("\n")
    bag_rules_tokenized.each do |rule|
      bag_rules.append(RuleExtractor.extract_rules_from_text(rule))
    end
    assert_equal(126, BagPacker.count_number_bags_needed(bag_rules, Bag.new('shiny', 'gold')))
  end

  def test_part_two
    bag_rules_text = File.read('./test/input.txt')
    bag_rules = []
    bag_rules_tokenized = bag_rules_text.split("\n")
    bag_rules_tokenized.each do |rule|
      bag_rules.append(RuleExtractor.extract_rules_from_text(rule))
    end
    assert_equal(7872, BagPacker.count_number_bags_needed(bag_rules, Bag.new('shiny', 'gold')))
  end
end
