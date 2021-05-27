# frozen_string_literal: true

require_relative 'expense_report_analyzer'
require 'test/unit'

class TestExpenseReportAnalyzer < Test::Unit::TestCase
  def test_smallest_list
    expenses = [2000, 20]
    summed_entries = ExpenseReportAnalyzer.find_sum_two_entries(expenses, 2020)
    assert_equal(summed_entries[0] * summed_entries[1], 40_000)
  end

  def test_normal_list
    expenses = [675, 979, 366, 299, 1721, 1456]
    summed_entries = ExpenseReportAnalyzer.find_sum_two_entries(expenses, 2020)
    assert_equal(summed_entries[0] * summed_entries[1], 514_579)
  end

  def test_puzzle_part_one
    expenses = File.read('input.txt').split.map(&:to_i)
    summed_entries = ExpenseReportAnalyzer.find_sum_two_entries(expenses, 2020)
    assert_equal(summed_entries[0] * summed_entries[1], 618_144)
  end

  def test_puzzle_part_two
    expenses = File.read('input.txt').split.map(&:to_i)
    summed_entries = ExpenseReportAnalyzer.find_sum_three_entries(expenses, 2020)
    assert_equal(summed_entries[0] * summed_entries[1] * summed_entries[2], 173_538_720)
  end
end
