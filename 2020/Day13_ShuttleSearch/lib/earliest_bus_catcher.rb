class EarliestBusCatcher
  def self.calculate_earliest_departure(station_arrival_time, bus_ids)
    wait_time = (2**(0.size * 8 - 2) - 1)
    bus_to_catch = 0

    bus_ids.each do |bus_id|
      next_wait_time = EarliestBusCatcher.calculate_wait_time(station_arrival_time, bus_id)
      wait_time = [next_wait_time, wait_time].min
      bus_to_catch = bus_id if wait_time == next_wait_time
    end

    { 'bus_id' => bus_to_catch, 'wait_time' => wait_time }
  end

  def self.calculate_wait_time(station_arrival_time, bus_id)
    mod = station_arrival_time % bus_id
    wait_time = 0
    wait_time = bus_id - mod unless mod.zero?
    wait_time
  end
end
