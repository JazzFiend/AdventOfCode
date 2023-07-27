from src.assembunny_command.assembunny_command import AssembunnyCommand
from src.assembunny_command_arguments import AssembunnyCommandArguments

class CommandJumpNotZero(AssembunnyCommand):
  def __init__(self, args: AssembunnyCommandArguments):
    super().__init__(args)
    if not isinstance(args.offset, int):
      raise RuntimeError("Offset is not an integer")
    self.offset = args.offset
    self.to_compare = args.to_compare

  def update_registers(self, registers: dict[str, int]) -> dict[str, int]:
    return registers
