import pytest
from src.assembunny_command_arguments import AssembunnyCommandArguments

from src.assembunny_command.command_decrement import CommandDecrement

def test_register_does_not_exist():
  with pytest.raises(RuntimeError):
    registers = {
      "r": 73
    }
    args = AssembunnyCommandArguments()
    args.register = "x"
    dec = CommandDecrement(args)
    dec.update_registers(registers)

def test_update_registers():
  registers = {
    "c": 87,
  }
  args = AssembunnyCommandArguments()
  args.register = "c"
  dec = CommandDecrement(args)
  assert dec.update_registers(registers) == { "c": 86 }

def test_update_pc():
  args = AssembunnyCommandArguments()
  args.register = "d"
  dec = CommandDecrement(args)
  assert dec.update_pc(None, 874) == 875
