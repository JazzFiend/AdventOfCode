# frozen_string_literal: true

class ExpenseReportAnalyzer
  def self.find_sum_two_entries(expense_list, sum)
    (0..expense_list.length - 1).each do |i|
      ((i + 1)..expense_list.length - 1).each do |j|
        return [expense_list[i], expense_list[j]] if expense_list[i] + expense_list[j] == sum
      end
    end
    raise 'Sum not found'
  end

  def self.find_sum_three_entries(expense_list, sum)
    (0..expense_list.length - 1).each do |i|
      ((i + 1)..expense_list.length - 1).each do |j|
        ((j + 1)..expense_list.length - 1).each do |k|
          if expense_list[i] + expense_list[j] + expense_list[k] == sum
            return [expense_list[i], expense_list[j], expense_list[k]]
          end
        end
      end
    end
    raise 'Sum not found'
  end
end
