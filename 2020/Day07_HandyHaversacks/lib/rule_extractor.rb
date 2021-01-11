require_relative('./bag')
require_relative('./bag_rule')

class RuleExtractor
  def self.extract_rules_from_text(rule_text)
    tokenized_string = rule_text.split(' ')
    bag = extract_containing_bag(tokenized_string[0], tokenized_string[1])
    contained_bags = extract_contained_bags(tokenized_string)
    BagRule.new(bag, contained_bags)
  end

  def self.extract_containing_bag(adjective, color)
    Bag.new(adjective, color)
  end

  def self.extract_contained_bags(tokenized_string)
    contained_bags = {}
    next_bag_pointer = 4
    more_bags_remain = at_least_one_bag?(tokenized_string[4])

    while more_bags_remain
      bag_text = tokenized_string[next_bag_pointer..next_bag_pointer + 3]
      add_contained_bag(bag_text, contained_bags)
      next_bag_pointer += 4
      more_bags_remain = false unless next_bag_exists?(tokenized_string, next_bag_pointer)
    end
    contained_bags
  end

  def self.at_least_one_bag?(potential_quantity)
    true unless potential_quantity == 'no'
  end

  def self.add_contained_bag(bag_text, contained_bags)
    inner_bag_quantity = bag_text[0].to_i
    contained_bags["#{bag_text[1]} #{bag_text[2]}"] = inner_bag_quantity
  end

  def self.next_bag_exists?(tokenized_string, next_bag_pointer)
    tokenized_string.length > next_bag_pointer + 1
  end
end
