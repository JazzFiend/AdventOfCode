import pytest
from src.assembunny_command.command_copy.command_copy_constant import CommandCopyConstant

from src.assembunny_command_arguments import AssembunnyCommandArguments

def test_error_changed_register_not_in_registers():
  with pytest.raises(RuntimeError):
    registers = {
      "a": -11
    }
    args = AssembunnyCommandArguments()
    args.register = "g"
    args.to_copy = 6
    cp = CommandCopyConstant(args)
    cp.update_registers(registers)

def test_error_copy_value_not_int():
  with pytest.raises(RuntimeError) as exc_info:
    args = AssembunnyCommandArguments()
    args.register = "a"
    args.to_copy = 10.345
    CommandCopyConstant(args)
  assert str(exc_info.value) == 'Copy value is not an int'

def test_run_copy_constant_to_register():
  registers = {
    "a": -11
  }
  args = AssembunnyCommandArguments()
  args.register = "a"
  args.to_copy = 4
  cp = CommandCopyConstant(args)
  assert cp.update_registers(registers) == { "a": 4 }

def test_update_pc():
  args = AssembunnyCommandArguments()
  args.register = "a"
  args.to_copy = 4
  cp = CommandCopyConstant(args)
  assert cp.update_pc(None, 5490) == 5491
