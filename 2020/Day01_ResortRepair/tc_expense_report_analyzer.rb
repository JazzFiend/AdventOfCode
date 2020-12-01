require_relative "expense_report_analyzer"
require "test/unit"

class TestExpenseReportAnalyzer < Test::Unit::TestCase
  def test_smallest_list
    expenses = [2000, 20]
    summedEntries = ExpenseReportAnalyzer.find_sum_two_entries(expenses, 2020)
    assert_equal(summedEntries[0] * summedEntries[1], 40000)
  end

  def test_normal_list
    expenses = [675, 979, 366, 299, 1721, 1456]
    summedEntries = ExpenseReportAnalyzer.find_sum_two_entries(expenses, 2020)
    assert_equal(summedEntries[0] * summedEntries[1], 514579)
  end

  def test_puzzle_part_1
    expenses = File.read("input.txt").split.map { |str| str.to_i }
    summedEntries = ExpenseReportAnalyzer.find_sum_two_entries(expenses, 2020)
    assert_equal(summedEntries[0] * summedEntries[1], 618144)
  end

  def test_puzzle_part_2
    expenses = File.read("input.txt").split.map { |str| str.to_i }
    summedEntries = ExpenseReportAnalyzer.find_sum_three_entries(expenses, 2020)
    assert_equal(summedEntries[0] * summedEntries[1] * summedEntries[2], 173538720)
  end
end