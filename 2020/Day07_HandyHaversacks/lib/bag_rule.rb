class BagRule
  attr_reader :bag, :contained_bags

  def initialize(bag, contained_bags)
    @bag = bag
    @contained_bags = contained_bags
  end

  def bag_description
    @bag.to_string
  end
end
