class ExpenseReportAnalyzer
  def self.find_sum_two_entries(expenseList, sum)
    for i in 0..expenseList.length() - 1
      for j in (i + 1)..expenseList.length() - 1
        if expenseList[i] + expenseList[j] == sum
          return [expenseList[i], expenseList[j]]
        end
      end
    end
    raise "Sum not found"
  end

  def self.find_sum_three_entries(expenseList, sum)
    for i in 0..expenseList.length() - 1
      for j in (i + 1)..expenseList.length() - 1
        for k in(j + 1)..expenseList.length() - 1
          if expenseList[i] + expenseList[j] + expenseList[k] == sum
            return [expenseList[i], expenseList[j], expenseList[k]]
          end
        end
      end
    end
    raise "Sum not found"
  end
end
