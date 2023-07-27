from src.assembunny_command.assembunny_command import AssembunnyCommand
from src.assembunny_command_arguments import AssembunnyCommandArguments

class CommandCopy(AssembunnyCommand):
  def __init__(self, args: AssembunnyCommandArguments):
    super().__init__(args)
    self.register = args.register

  def update_registers(self, registers: dict[str, int]) -> dict[str, int]:
    AssembunnyCommand.validate_register(registers, self.register)
