require 'set'

class BagMapper
  def self.count_bag_packing_methods(bag_rules, seed_bag)
    base_case = Set[]
    base_case.add(seed_bag)
    all_bag_packing_methods = count_bag_packing_methods_recursive(bag_rules, seed_bag, base_case)
    all_bag_packing_methods.delete(seed_bag)
    all_bag_packing_methods.length
  end

  def self.count_bag_packing_methods_recursive(bag_rules, seed_bag, seen_bags)
    current_seen_bags = Set[]
    current_seen_bags.merge(seen_bags)
    new_bags = determine_new_bags(bag_rules, seed_bag, seen_bags)

    current_seen_bags.merge(new_bags)
    new_bags.each do |new_bag|
      current_seen_bags.merge(count_bag_packing_methods_recursive(bag_rules, new_bag, current_seen_bags))
    end

    current_seen_bags
  end
end

def determine_new_bags(bag_rules, seed_bag, seen_bags)
  new_bags = []
  bag_rules.each do |bag_rule|
    bag_rule.contained_bags.each_key do |bag|
      if !bag_seen?(seen_bags, bag_rule.bag_description) && bag == seed_bag.to_string
        tokened_bag = bag_rule.bag_description.split(' ')
        new_bags.append(Bag.new(tokened_bag[0], tokened_bag[1]))
      end
    end
  end
  new_bags
end

def bag_seen?(seen_bags, bag)
  tokened_bag = bag.split(' ')
  seen_bags.each do |seen_bag|
    true if seen_bag.adjective == tokened_bag[0] && seen_bag.color == tokened_bag[1]
  end
  false
end

