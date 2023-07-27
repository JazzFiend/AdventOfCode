import pytest
from src.assembunny_command_arguments import AssembunnyCommandArguments

from src.assembunny_command.command_increment import CommandIncrement

def test_bad_register():
  with pytest.raises(RuntimeError):
    registers = {
      "m": 34
    }
    args = AssembunnyCommandArguments()
    args.register = "x"
    inc = CommandIncrement(args)
    inc.update_registers(registers)

def test_run():
  registers = {
    "b": 5,
  }
  args = AssembunnyCommandArguments()
  args.register = "b"
  inc = CommandIncrement(args)
  assert inc.update_registers(registers) == { "b": 6 }

def test_update_pc():
  args = AssembunnyCommandArguments()
  args.register = "o"
  inc = CommandIncrement(args)
  assert inc.update_pc(None, 542) == 543
