require_relative '../lib/naive_seating_simulator'

require 'minitest/autorun'

class TestNaiveSeatingSimulator < Minitest::Test
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
    starting_seating_chart = ['.#.', '###', '.#.']
    expected_seating_chart = '.#.\n#L#\n.#.'

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

  def test_acceptance
    starting_seating_chart = [
      '#.##.L#.##',
      '#L###LL.L#',
      'L.#.#..#..',
      '#L##.##.L#',
      '#.##.LL.LL',
      '#.###L#.##',
      '..#.#.....',
      '#L######L#',
      '#.LL###L.L',
      '#.#L###.##'
    ]

    expected_seating_chart = '#.#L.L#.##'
    expected_seating_chart += '\n#LLL#LL.L#'
    expected_seating_chart += '\nL.L.L..#..'
    expected_seating_chart += '\n#LLL.##.L#'
    expected_seating_chart += '\n#.LL.LL.LL'
    expected_seating_chart += '\n#.LL#L#.##'
    expected_seating_chart += '\n..L.L.....'
    expected_seating_chart += '\n#L#LLLL#L#'
    expected_seating_chart += '\n#.LLLLLL.L'
    expected_seating_chart += '\n#.#L#L#.##'

    assert_seating_chart_correct(starting_seating_chart, expected_seating_chart)
  end

  def assert_seating_chart_correct(starting_seating_chart, expected_seating_chart)
    seating_simulator = NaiveSeatingSimulator.new(starting_seating_chart)
    seating_simulator.advance_turn
    assert_equal(expected_seating_chart, seating_simulator.readable_seating_chart)
  end
end
