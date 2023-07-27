from src.assembunny_command.command_copy.command_copy import CommandCopy
from src.assembunny_command_arguments import AssembunnyCommandArguments

class CommandCopyRegister(CommandCopy):
  def __init__(self, args: AssembunnyCommandArguments):
    super().__init__(args)
    if not isinstance(args.to_copy, str):
      raise RuntimeError("Copy value is not a string")
    self.to_copy = args.to_copy

  def update_registers(self, registers: dict[str, int]) -> dict[str, int]:
    super().update_registers(registers)
    new_registers = registers
    if self.to_copy in new_registers:
      new_registers[self.register] = new_registers[self.to_copy]
    else:
      raise RuntimeError("Copy value is not in register map")

    return new_registers
