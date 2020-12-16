require_relative '../lib/question_compiler'

require 'minitest/autorun'

class TestQuestionCompiler < Minitest::Test
  def test_single_question_unique
    group_yes_answers = 'a'
    assert_equal(1, QuestionCompiler.count_unique_yes_answers(group_yes_answers))
  end

  def test_multiple_people_single_question_unique
    group_yes_answers = "a\na\na\na"
    assert_equal(1, QuestionCompiler.count_unique_yes_answers(group_yes_answers))
  end

  def test_multiple_questions_unique
    group_yes_answers = "abcx\nabcy\nabcz"
    assert_equal(6, QuestionCompiler.count_unique_yes_answers(group_yes_answers))
  end

  def test_single_question_collective
    group_yes_answers = 'a'
    assert_equal(1, QuestionCompiler.count_collective_yes_answers(group_yes_answers))
  end

  def test_multiple_people_single_question_collective
    group_yes_answers = "a\na\na\na"
    assert_equal(1, QuestionCompiler.count_collective_yes_answers(group_yes_answers))
  end

  def test_multiple_questions_collective
    group_yes_answers = "abcx\nabcy\nabcz"
    assert_equal(3, QuestionCompiler.count_collective_yes_answers(group_yes_answers))
  end
end
