require_relative '../lib/command'

RSpec.describe Command do
  class IncorrectCommand < Command
  end

  it 'Command\'s execute function should be abstract' do
    expect { IncorrectCommand.new.execute }.to raise_error('Abstract Function')
  end
end
