class BagPacker
  def self.count_number_bags_needed(bag_rules, desired_bag)
    total_bags = 0
    bag_rules.each do |bag_rule|
      next unless bag_rule.bag.eql?(desired_bag)

      bag_rule.contained_bags.each_key do |bag_key|
        number_of_bags = bag_rule.contained_bags[bag_key]
        total_bags += number_of_bags + number_of_bags * count_number_bags_needed(bag_rules, string_to_bag(bag_key))
      end
      break
    end
    total_bags
  end

  def self.string_to_bag(bag_name)
    tokenized_bag = bag_name.split(' ')
    Bag.new(tokenized_bag[0], tokenized_bag[1])
  end
end
