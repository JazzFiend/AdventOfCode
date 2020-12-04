class PasswordRuleV1
  attr_reader :min_char, :max_char, :char

  def initialize(password_rule_string)
    @min_char = extract_min_char(password_rule_string)
    @max_char = extract_max_char(password_rule_string)
    @char = extract_char(password_rule_string)
  end

  private

  def extract_min_char(rule_string)
    tokenized_by_dash = rule_string.split('-')
    tokenized_by_dash[0].to_i
  end

  def extract_max_char(rule_string)
    tokenized_by_dash = rule_string.split('-')
    tokenized_by_space = tokenized_by_dash[1].split(' ')
    tokenized_by_space[0].to_i
  end

  def extract_char(rule_string)
    tokenized_by_dash = rule_string.split('-')
    tokenized_by_space = tokenized_by_dash[1].split(' ')
    tokenized_by_space[1][0]
  end
end
