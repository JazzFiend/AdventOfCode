from src.assembunny_command_arguments import AssembunnyCommandArguments

class AssembunnyCommand():
  def __init__(self, args: AssembunnyCommandArguments):
    pass

  def update_registers(self, registers: dict[str, int]) -> dict[str, int]:
    pass

  def update_pc(self, registers: dict[str, int], pc: int) -> int:
    return pc + 1

  @staticmethod
  def validate_register(registers: dict[str, int], register: str):
    if register not in registers:
      raise RuntimeError("Register not in registers")
