import pytest
from src.assembunny_command.command_copy.command_copy_constant import CommandCopyConstant
from src.assembunny_command.command_copy.command_copy_register import CommandCopyRegister
from src.assembunny_command.command_decrement import CommandDecrement
from src.assembunny_command.command_increment import CommandIncrement
from src.assembunny_command.command_jump_not_zero.command_jump_not_zero_constant import CommandJumpNotZeroConstant
from src.assembunny_command.command_jump_not_zero.command_jump_not_zero_register import CommandJumpNotZeroRegister
from src.decoder import Decoder

def test_empty_string_should_throw():
  with pytest.raises(RuntimeError):
    Decoder.decode_instruction("")

def test_bad_string_should_throw():
  with pytest.raises(RuntimeError):
    Decoder.decode_instruction("dnk o")

def test_dec_instruction():
  dec = Decoder.decode_instruction("dec p")
  assert isinstance(dec, CommandDecrement)
  assert dec.register == "p"

def test_inc_instruction():
  inc = Decoder.decode_instruction("inc d")
  assert isinstance(inc, CommandIncrement)
  assert inc.register == "d"

def test_jnz_instruction_constant():
  jnz = Decoder.decode_instruction("jnz 9 3")
  assert isinstance(jnz, CommandJumpNotZeroConstant)
  assert jnz.to_compare == 9
  assert jnz.offset == 3

def test_jnz_instruction_register():
  jnz = Decoder.decode_instruction("jnz r 3")
  assert isinstance(jnz, CommandJumpNotZeroRegister)
  assert jnz.to_compare == "r"
  assert jnz.offset == 3

def test_cpy_instruction_constant():
  cpy = Decoder.decode_instruction("cpy 9 i")
  assert isinstance(cpy, CommandCopyConstant)
  assert cpy.to_copy == 9
  assert cpy.register == "i"

def test_cpy_instruction_register():
  cpy = Decoder.decode_instruction("cpy v k")
  assert isinstance(cpy, CommandCopyRegister)
  assert cpy.to_copy == "v"
  assert cpy.register == "k"
