require_relative '../lib/consecutive_bus_calculator'

RSpec.describe ConsecutiveBusCalculator, '#Determine earliest time when all buses leave consecutively' do
  it 'A single bus should return a consecutive of 0' do
    expect(ConsecutiveBusCalculator.calculate_earliest_consecutive([1])).to eq 0
  end

  it 'Two buses' do
    expect(ConsecutiveBusCalculator.calculate_earliest_consecutive([1, 2])).to eq 1
  end

  it 'Placeholder bus' do
    expect(ConsecutiveBusCalculator.calculate_earliest_consecutive([1, 'x', 2])).to eq 0
  end

  it 'Acceptance tests' do
    expect(ConsecutiveBusCalculator.chinese_remainder([7, 13, 'x', 'x', 59, 'x', 31, 19])).to eq 1_068_781
    expect(ConsecutiveBusCalculator.chinese_remainder([17, 'x', 13, 19])).to eq 3417
    expect(ConsecutiveBusCalculator.chinese_remainder([67, 7, 59, 61])).to eq 754_018
    expect(ConsecutiveBusCalculator.chinese_remainder([67, 'x', 7, 59, 61])).to eq 779_210
    expect(ConsecutiveBusCalculator.chinese_remainder([67, 7, 'x', 59, 61])).to eq 1_261_476
    expect(ConsecutiveBusCalculator.chinese_remainder([1789, 37, 47, 1889])).to eq 1_202_161_486
  end

  it 'Puzzle Two' do
    bus_schedule = [37, 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x',
                    'x', 'x', 'x', 'x', 'x', 'x', 'x', 41, 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 601, 'x', 'x',
                    'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 19, 'x', 'x', 'x', 'x', 17, 'x', 'x', 'x', 'x', 'x',
                    23, 'x', 'x', 'x', 'x', 'x', 29, 'x', 443, 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x',
                    'x', 13]
    expect(ConsecutiveBusCalculator.chinese_remainder(bus_schedule)).to eq 379_786_358_533_423
  end
end
