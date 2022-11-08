require_relative '../../lib/v2/instruction_decoder_v2'
require_relative '../../lib/memory_data'

RSpec.describe InstructionDecoderV2 do
  dummy_memory = MemoryData.new([], {})
  decoder = InstructionDecoderV2.new
  it 'No instruction given should raise an error' do
    expect { decoder.decode_instruction(dummy_memory, '') }.to raise_error('Bad instruction seen')
  end

  it 'Mask instruction' do
    command = decoder.decode_instruction(dummy_memory, 'mask = 1X100')
    expect(command.class).to eq MaskCommand
  end

  it 'Memory instruction' do
    command = decoder.decode_instruction(dummy_memory, 'mem[2] = 100')
    expect(command.class).to eq MemoryCommandV2
  end
end
