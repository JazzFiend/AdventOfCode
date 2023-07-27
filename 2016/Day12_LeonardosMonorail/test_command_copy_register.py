import pytest
from src.assembunny_command.command_copy.command_copy_register import CommandCopyRegister

from src.assembunny_command_arguments import AssembunnyCommandArguments

def test_error_copy_value_not_in_registers():
  with pytest.raises(RuntimeError):
    registers = {
      "a": -11
    }
    args = AssembunnyCommandArguments()
    args.register = "a"
    args.to_copy = "e"
    cp = CommandCopyRegister(args)
    cp.update_registers(registers)

def test_error_changed_register_not_in_registers():
  with pytest.raises(RuntimeError):
    registers = {
      "a": -11
    }
    args = AssembunnyCommandArguments()
    args.register = "g"
    args.to_copy = 6
    cp = CommandCopyRegister(args)
    cp.update_registers(registers)

def test_error_copy_value_not_int_or_string():
  with pytest.raises(RuntimeError) as exc_info:
    args = AssembunnyCommandArguments()
    args.register = "a"
    args.to_copy = 10.345
    CommandCopyRegister(args)
  assert str(exc_info.value) == 'Copy value is not a string'

def test_run_copy_register_to_register():
  registers = {
    "x": 45687,
    "y": 5849
  }
  expected = {
    "x": 45687,
    "y": 45687
  }
  args = AssembunnyCommandArguments()
  args.register = "y"
  args.to_copy = "x"
  cp = CommandCopyRegister(args)
  assert cp.update_registers(registers) == expected

def test_update_pc():
  args = AssembunnyCommandArguments()
  args.register = "y"
  args.to_copy = "x"
  cp = CommandCopyRegister(args)
  assert cp.update_pc(None, 5490) == 5491
