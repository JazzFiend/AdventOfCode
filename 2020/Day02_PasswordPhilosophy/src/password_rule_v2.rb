class PasswordRuleV2
  attr_reader :positions, :char

  def initialize(password_rule_string)
    @positions = extract_positions(password_rule_string)
    @char = extract_char(password_rule_string)
  end

  private

  def extract_positions(rule_string)
    positions = []
    tokenized_by_dash = rule_string.split('-')
    positions.append(tokenized_by_dash[0].to_i)
    tokenized_by_space = tokenized_by_dash[1].split(' ')
    positions.append(tokenized_by_space[0].to_i)
    positions
  end

  def extract_char(rule_string)
    tokenized_by_dash = rule_string.split('-')
    tokenized_by_space = tokenized_by_dash[1].split(' ')
    tokenized_by_space[1][0]
  end
end
