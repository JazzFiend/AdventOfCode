from src.assembunny_command.command_jump_not_zero.command_jump_not_zero import CommandJumpNotZero
from src.assembunny_command_arguments import AssembunnyCommandArguments


class CommandJumpNotZeroConstant(CommandJumpNotZero):
  def __init__(self, args: AssembunnyCommandArguments):
    if not isinstance(args.to_compare, int):
      raise RuntimeError("Compare value is not an integer")
    super().__init__(args)

  def update_pc(self, registers: dict[str, int], pc: int) -> int:
    if self.to_compare == 0:
      return super().update_pc(registers, pc)
    return pc + self.offset
