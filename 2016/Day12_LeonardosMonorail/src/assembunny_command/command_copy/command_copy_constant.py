from src.assembunny_command.command_copy.command_copy import CommandCopy
from src.assembunny_command_arguments import AssembunnyCommandArguments


class CommandCopyConstant(CommandCopy):
  def __init__(self, args: AssembunnyCommandArguments):
    super().__init__(args)
    if not isinstance(args.to_copy, int):
      raise RuntimeError("Copy value is not an int")
    self.to_copy = args.to_copy

  def update_registers(self, registers: dict[str, int]) -> dict[str, int]:
    super().update_registers(registers)
    new_registers = registers
    new_registers[self.register] = self.to_copy
    return new_registers
