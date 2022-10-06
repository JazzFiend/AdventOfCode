require_relative '../lib/earliest_bus_catcher'

RSpec.describe EarliestBusCatcher, '#calculate earliest catchable bus' do
  it 'Arrival time 0 and bus 1 should result in leaving at time 0' do
    assert_earliest_bus(EarliestBusCatcher.calculate_earliest_departure(0, [1]), 1, 0)
  end

  it 'Arrival Time occurs after the buses start moving' do
    assert_earliest_bus(EarliestBusCatcher.calculate_earliest_departure(3, [1]), 1, 0)
  end

  it 'Arrive at time 0 but the bus id is greater than 1' do
    assert_earliest_bus(EarliestBusCatcher.calculate_earliest_departure(0, [4]), 4, 0)
  end

  it 'Arrive before next bus departs, resulting in a wait time' do
    assert_earliest_bus(EarliestBusCatcher.calculate_earliest_departure(7, [5]), 5, 3)
  end

  it 'Arrive long after buses start departing' do
    assert_earliest_bus(EarliestBusCatcher.calculate_earliest_departure(56, [10]), 10, 4)
  end

  it 'Return the lowest wait time when given multiple buses' do
    assert_earliest_bus(EarliestBusCatcher.calculate_earliest_departure(27, [5, 7]), 7, 1)
  end

  it 'Acceptance Test' do
    assert_earliest_bus(EarliestBusCatcher.calculate_earliest_departure(939, [7, 13, 59, 31, 19]), 59, 5)
  end

  it 'Puzzle One' do
    bus_ids = [37, 41, 601, 19, 17, 23, 29, 443, 13]
    assert_earliest_bus(EarliestBusCatcher.calculate_earliest_departure(1_002_462, bus_ids), 601, 6)
  end

  def assert_earliest_bus(earliest_bus_data, expected_bus_id, expected_wait_time)
    expect(earliest_bus_data['bus_id']).to eq expected_bus_id
    expect(earliest_bus_data['wait_time']).to eq expected_wait_time
  end
end
