include RSpec::Matchers

class CommandHelper
  def self.assert_same_memory_data(data_recieved, data_expected)
    expect(data_recieved.mask).to eq data_expected.mask

    recieved = data_recieved.memory
    expected = data_expected.memory
    expect(recieved.size).to eq expected.size
    recieved.keys.map { |key| expect(recieved[key]).to eq expected[key] }
  end
end
