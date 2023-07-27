from src.decoder import Decoder

class AssembunnyCpu:
  def __init__(self, a:int, b:int, c:int, d:int):
    self.registers = {
      "a": a,
      "b": b,
      "c": c,
      "d": d
    }

  def run(self, instructions: list[str]) -> None:
    pc = 0
    while pc >= 0 and pc < len(instructions):
      command = Decoder.decode_instruction(instructions[pc])
      self.registers = command.update_registers(self.registers)
      pc = command.update_pc(self.registers, pc)

  def get_register_value(self, register: str) -> int:
    return self.registers[register]
