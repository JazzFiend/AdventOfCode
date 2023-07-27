import pytest
from src.assembunny_command.command_jump_not_zero.command_jump_not_zero_constant import CommandJumpNotZeroConstant

from src.assembunny_command_arguments import AssembunnyCommandArguments

def test_error_offset_not_number():
  with pytest.raises(RuntimeError):
    args = AssembunnyCommandArguments
    args.to_compare = "t"
    args.offset = "19"
    CommandJumpNotZeroConstant(args)

def test_error_to_compare_not_int():
  with pytest.raises(RuntimeError) as exc_info:
    args = AssembunnyCommandArguments
    args.to_compare = "43"
    args.offset = 3
    CommandJumpNotZeroConstant(args)
  assert str(exc_info.value) == 'Compare value is not an integer'

def test_modify_register():
  registers = {
    "s": 34,
    "p": 96
  }
  args = AssembunnyCommandArguments()
  args.to_compare = 5
  args.offset = 89
  jump = CommandJumpNotZeroConstant(args)
  assert jump.update_registers(registers) == registers

def test_no_jump_constant():
  args = AssembunnyCommandArguments
  args.to_compare = 0
  args.offset = 40
  jump = CommandJumpNotZeroConstant(args)
  pc = 10
  assert jump.update_pc(None, pc) == 11

def test_jump_constant():
  args = AssembunnyCommandArguments
  args.to_compare = 7
  args.offset = 50
  jump = CommandJumpNotZeroConstant(args)
  pc = 10
  assert jump.update_pc(None, pc) == 60

def test_jump_negative_number():
  args = AssembunnyCommandArguments
  args.to_compare = -5
  args.offset = 100
  jump = CommandJumpNotZeroConstant(args)
  pc = 10
  assert jump.update_pc(None, pc) == 110
