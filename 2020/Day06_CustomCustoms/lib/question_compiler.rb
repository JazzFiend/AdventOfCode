require 'set'

class QuestionCompiler
  def self.count_unique_yes_answers(group_yes_answers)
    yes_answers = Set[]
    group_yes_answers.split('').each do |char|
      yes_answers.add(char) unless ["\n", ' '].include?(char)
    end
    yes_answers.size
  end

  def self.count_collective_yes_answers(group_yes_answers)
    common_questions = Set['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']
    group_yes_answers.split(' ').each do |answer_list|
      current_yes_answers = Set[]
      answer_list.split('').each do |char|
        current_yes_answers.add(char)
      end
      common_questions &= current_yes_answers
    end
    common_questions.size
  end
end
