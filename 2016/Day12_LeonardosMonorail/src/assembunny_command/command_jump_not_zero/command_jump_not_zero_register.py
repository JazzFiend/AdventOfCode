from src.assembunny_command.command_jump_not_zero.command_jump_not_zero import CommandJumpNotZero
from src.assembunny_command_arguments import AssembunnyCommandArguments

class CommandJumpNotZeroRegister(CommandJumpNotZero):
  def __init__(self, args: AssembunnyCommandArguments):
    if not isinstance(args.to_compare, str):
      raise RuntimeError("Compare value is not a string")
    super().__init__(args)

  def update_pc(self, registers: dict[str, int], pc: int) -> int:
    if self.extract_compare_value(registers) == 0:
      return super().update_pc(registers, pc)
    return pc + self.offset

  def extract_compare_value(self, registers):
    if self.to_compare not in registers:
      raise RuntimeError("Compare value not in registers")
    return registers[self.to_compare]
