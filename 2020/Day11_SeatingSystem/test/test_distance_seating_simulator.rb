require_relative '../lib/distance_seating_simulator'

require 'minitest/autorun'

class TestDistanceSeatingSimulator < Minitest::Test
  def test_empty_grid
    assert_seating_chart_correct([''], '')
  end

  def test_one_floor
    assert_seating_chart_correct(['.'], '.')
  end

  def test_one_seat_turns_occupied
    assert_seating_chart_correct(['L'], '#')
  end

  def test_one_occupied
    assert_seating_chart_correct(['#'], '#')
  end

  def test_occupied_turns_empty
    starting_seating_chart = ['##.', '###', '.#.']
    expected_seating_chart = '##.\n#L#\n.#.'

    assert_seating_chart_correct(starting_seating_chart, expected_seating_chart)
  end

  def test_empty_stays_empty
    starting_seating_chart = ['.L.', '#LL', '.L.']
    expected_seating_chart = '.L.\n#L#\n.L.'

    assert_seating_chart_correct(starting_seating_chart, expected_seating_chart)
  end

  def test_no_seats_taken
    starting_seating_chart = ['LLL', 'LLL', 'LLL']
    expected_seating_chart = '###\n###\n###'

    assert_seating_chart_correct(starting_seating_chart, expected_seating_chart)
  end

  def test_one_seats_taken
    starting_seating_chart = ['#LL', 'LLL', 'LLL']
    expected_seating_chart = '#L#\nLL#\n###'

    assert_seating_chart_correct(starting_seating_chart, expected_seating_chart)
  end

  def test_previous_changes_dont_affect_current
    starting_seating_chart = ['###', '###', '###']
    expected_seating_chart = '#L#\nLLL\n#L#'

    assert_seating_chart_correct(starting_seating_chart, expected_seating_chart)
  end

  def test_not_square
    starting_seating_chart = ['LLLL', 'LLLL', 'LLLL']
    expected_seating_chart = '####\n####\n####'

    assert_seating_chart_correct(starting_seating_chart, expected_seating_chart)
  end

  def test_empty_stays_empty_at_distance_with_one_occupied
    starting_seating_chart = ['.........', '...#.....', '.........', '...L.....']
    expected_seating_chart = '.........\n...#.....\n.........\n...L.....'

    assert_seating_chart_correct(starting_seating_chart, expected_seating_chart)
  end

  def test_dont_check_past_first_seen_seat
    starting_seating_chart = ['.L.L.#.#.#.#.']
    expected_seating_chart = '.#.L.#.#.#.#.'

    assert_seating_chart_correct(starting_seating_chart, expected_seating_chart)
  end

  def test_no_occupied_seats_at_a_distance
    starting_seating_chart = ['.##.##.', '#.#.#.#', '##...##', '...L...', '##...##', '#.#.#.#', '.##.##.']
    expected_seating_chart = '.#L.L#.\n#.L.L.#\nLL...LL\n...#...\nLL...LL\n#.L.L.#\n.#L.L#.'

    assert_seating_chart_correct(starting_seating_chart, expected_seating_chart)
  end

  def test_seat_becomes_empty_at_a_distance
    starting_seating_chart = ['#.#..', '.....', '..#.#', '.....', '#...#']
    expected_seating_chart = '#.#..\n.....\n..L.#\n.....\n#...#'

    assert_seating_chart_correct(starting_seating_chart, expected_seating_chart)
  end

  def test_acceptance
    starting_seating_chart = [
      '#.L#.L#.L#',
      '#LLLLLL.LL',
      'L.L.L..#..',
      '##LL.LL.L#',
      'L.LL.LL.L#',
      '#.LLLLL.LL',
      '..L.L.....',
      'LLLLLLLLL#',
      '#.LLLLL#.L',
      '#.L#LL#.L#'
    ]

    expected_seating_chart = '#.L#.L#.L#\n'
    expected_seating_chart += '#LLLLLL.LL\n'
    expected_seating_chart += 'L.L.L..#..\n'
    expected_seating_chart += '##L#.#L.L#\n'
    expected_seating_chart += 'L.L#.#L.L#\n'
    expected_seating_chart += '#.L####.LL\n'
    expected_seating_chart += '..#.#.....\n'
    expected_seating_chart += 'LLL###LLL#\n'
    expected_seating_chart += '#.LLLLL#.L\n'
    expected_seating_chart += '#.L#LL#.L#'

    assert_seating_chart_correct(starting_seating_chart, expected_seating_chart)
  end

  def assert_seating_chart_correct(starting_seating_chart, expected_seating_chart)
    seating_simulator = DistanceSeatingSimulator.new(starting_seating_chart)
    seating_simulator.advance_turn
    assert_equal(expected_seating_chart, seating_simulator.readable_seating_chart)
  end
end
