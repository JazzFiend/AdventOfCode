from src.assembunny_command.assembunny_command import AssembunnyCommand
from src.assembunny_command.command_copy.command_copy_constant import CommandCopyConstant
from src.assembunny_command.command_copy.command_copy_register import CommandCopyRegister
from src.assembunny_command.command_jump_not_zero.command_jump_not_zero_constant import CommandJumpNotZeroConstant
from src.assembunny_command.command_jump_not_zero.command_jump_not_zero_register import CommandJumpNotZeroRegister
from src.assembunny_command_arguments import AssembunnyCommandArguments
from src.assembunny_command.command_decrement import CommandDecrement
from src.assembunny_command.command_increment import CommandIncrement

class Decoder():
  @staticmethod
  def decode_instruction(instruction: str) -> AssembunnyCommand:
    if len(instruction) == 0:
      raise RuntimeError("Invalid Instruction")

    tokenized_instruction = instruction.split()
    args = AssembunnyCommandArguments()
    if tokenized_instruction[0] == "dec":
      args.register = tokenized_instruction[1]
      return CommandDecrement(args)
    if tokenized_instruction[0] == "inc":
      args.register = tokenized_instruction[1]
      return CommandIncrement(args)
    if tokenized_instruction[0] == "jnz":
      if Decoder.is_int(tokenized_instruction[1]):
        args.to_compare = int(tokenized_instruction[1])
        args.offset = int(tokenized_instruction[2])
        return CommandJumpNotZeroConstant(args)
      args.to_compare = tokenized_instruction[1]
      args.offset = int(tokenized_instruction[2])
      return CommandJumpNotZeroRegister(args)
    if tokenized_instruction[0] == "cpy":
      if Decoder.is_int(tokenized_instruction[1]):
        args.to_copy = int(tokenized_instruction[1])
        args.register = tokenized_instruction[2]
        return CommandCopyConstant(args)
      args.to_copy = tokenized_instruction[1]
      args.register = tokenized_instruction[2]
      return CommandCopyRegister(args)
    raise RuntimeError("Invalid Instruction")

  @staticmethod
  def is_int(string):
    try:
      int(string)
      return True
    except ValueError:
      return False
