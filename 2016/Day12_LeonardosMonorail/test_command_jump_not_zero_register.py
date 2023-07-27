import pytest
from src.assembunny_command.command_jump_not_zero.command_jump_not_zero_register import CommandJumpNotZeroRegister

from src.assembunny_command_arguments import AssembunnyCommandArguments

def test_error_offset_not_number():
  with pytest.raises(RuntimeError):
    args = AssembunnyCommandArguments
    args.to_compare = "t"
    args.offset = "19"
    CommandJumpNotZeroRegister(args)

def test_error_bad_register():
  with pytest.raises(RuntimeError) as exc_info:
    registers = {
      "t": 7
    }
    args = AssembunnyCommandArguments()
    args.to_compare = "k"
    args.offset = 94
    pc = 84
    jump = CommandJumpNotZeroRegister(args)
    jump.update_pc(registers, pc)
  assert str(exc_info.value) == 'Compare value not in registers'

def test_error_to_compare_not_valid():
  with pytest.raises(RuntimeError) as exc_info:
    args = AssembunnyCommandArguments
    args.to_compare = 445
    args.offset = 3
    CommandJumpNotZeroRegister(args)
  assert str(exc_info.value) == 'Compare value is not a string'

def test_modify_register():
  registers = {
    "s": 34,
    "p": 96
  }
  args = AssembunnyCommandArguments()
  args.to_compare = "t"
  args.offset = 89
  jump = CommandJumpNotZeroRegister(args)
  assert jump.update_registers(registers) == registers

def test_no_jump_register():
  registers = {
    "h": 0
  }
  args = AssembunnyCommandArguments
  args.to_compare = "h"
  args.offset = 100
  jump = CommandJumpNotZeroRegister(args)
  pc = 10
  assert jump.update_pc(registers, pc) == 11

def test_jump_register():
  registers = {
    "h": 1
  }
  args = AssembunnyCommandArguments
  args.to_compare = "h"
  args.offset = 100
  jump = CommandJumpNotZeroRegister(args)
  pc = 10
  assert jump.update_pc(registers, pc) == 110

def test_jump_negative_number():
  registers = {
    "h": -1
  }
  args = AssembunnyCommandArguments
  args.to_compare = "h"
  args.offset = 100
  jump = CommandJumpNotZeroRegister(args)
  pc = 10
  assert jump.update_pc(registers, pc) == 110
