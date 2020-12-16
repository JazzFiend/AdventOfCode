require_relative './question_compiler'

class PlaneCustomsCalculator
  def self.calculate_unique_yes_count(group_yes_answers)
    groups = split_by_group(group_yes_answers)
    aggregate_unique_yes_answers(groups)
  end

  def self.split_by_group(group_yes_answers)
    group_yes_answers_split = group_yes_answers.split("\n")
    groups = []
    group_to_add = ''

    group_yes_answers_split.each do |line|
      if line == ''
        groups.append(group_to_add)
        group_to_add = ''
      else
        group_to_add += ' '
        group_to_add += line
      end
    end
    groups.append(group_to_add)
    groups
  end

  def self.calculate_collective_yes_count(group_yes_answers)
    groups = split_by_group(group_yes_answers)
    aggregate_collective_yes_answers(groups)
  end

  def self.aggregate_unique_yes_answers(groups)
    sum = 0
    groups.each do |group|
      sum += QuestionCompiler.count_unique_yes_answers(group)
    end
    sum
  end

  def self.aggregate_collective_yes_answers(groups)
    sum = 0
    groups.each do |group|
      sum += QuestionCompiler.count_collective_yes_answers(group)
    end
    sum
  end
end
