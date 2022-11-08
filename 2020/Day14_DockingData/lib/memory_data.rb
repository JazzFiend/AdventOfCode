class MemoryData
  attr_reader :mask, :memory

  def initialize(mask, memory)
    @mask = mask
    @memory = memory
  end
end
