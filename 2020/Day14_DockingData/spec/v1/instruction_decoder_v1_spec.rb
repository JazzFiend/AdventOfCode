require_relative '../../lib/v1/instruction_decoder_v1'
require_relative '../../lib/memory_data'

RSpec.describe InstructionDecoderV1 do
  dummy_memory = MemoryData.new([], {})
  decoder = InstructionDecoderV1.new
  it 'No instruction given should raise an error' do
    expect { decoder.decode_instruction(dummy_memory, '') }.to raise_error('Bad instruction seen')
  end

  it 'Mask instruction' do
    command = decoder.decode_instruction(dummy_memory, 'mask = XX1')
    expect(command.class).to eq MaskCommand
  end

  it 'Memory instruction' do
    command = decoder.decode_instruction(dummy_memory, 'mem[14] = 22')
    expect(command.class).to eq MemoryCommandV1
  end
end
