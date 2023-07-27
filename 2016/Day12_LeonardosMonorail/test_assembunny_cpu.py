import pytest

from src.assembunny_cpu import AssembunnyCpu

def test_no_instructions_default_cpu():
  assembunny_cpu = AssembunnyCpu(0, 0, 0, 0)
  assembunny_cpu.run([])
  assert_register_values(assembunny_cpu, 0, 0, 0, 0)

def test_single_instruction():
  assembunny_cpu = AssembunnyCpu(0, 0, 0, 0)
  assembunny_cpu.run(["inc c"])
  assert_register_values(assembunny_cpu, 0, 0, 1, 0)

def test_multiple_instruction():
  assembunny_cpu = AssembunnyCpu(0, 0, 0, 0)
  assembunny_cpu.run(["inc a", "inc a", "cpy a b"])
  assert_register_values(assembunny_cpu, 2, 2, 0, 0)

def test_branch_instruction():
  assembunny_cpu = AssembunnyCpu(0, 0, 0, 0)
  assembunny_cpu.run(["cpy -10 d", "inc d", "jnz d -1", "dec b"])
  assert_register_values(assembunny_cpu, 0, -1, 0, 0)

def test_acceptance():
  instructions = [
    "cpy 41 a",
    "inc a",
    "inc a",
    "dec a",
    "jnz a 2",
    "dec a"
  ]
  assembunny_cpu = AssembunnyCpu(0, 0, 0, 0)
  assembunny_cpu.run(instructions)
  assert assembunny_cpu.get_register_value("a") == 42

def test_puzzle_one():
  instructions = [
    "cpy 1 a",
    "cpy 1 b",
    "cpy 26 d",
    "jnz c 2",
    "jnz 1 5",
    "cpy 7 c",
    "inc d",
    "dec c",
    "jnz c -2",
    "cpy a c",
    "inc a",
    "dec b",
    "jnz b -2",
    "cpy c b",
    "dec d",
    "jnz d -6",
    "cpy 16 c",
    "cpy 17 d",
    "inc a",
    "dec d",
    "jnz d -2",
    "dec c",
    "jnz c -5",
  ]
  assembunny_cpu = AssembunnyCpu(0, 0, 0, 0)
  assembunny_cpu.run(instructions)
  assert assembunny_cpu.get_register_value("a") == 318083

def test_puzzle_two():
  instructions = [
    "cpy 1 a",
    "cpy 1 b",
    "cpy 26 d",
    "jnz c 2",
    "jnz 1 5",
    "cpy 7 c",
    "inc d",
    "dec c",
    "jnz c -2",
    "cpy a c",
    "inc a",
    "dec b",
    "jnz b -2",
    "cpy c b",
    "dec d",
    "jnz d -6",
    "cpy 16 c",
    "cpy 17 d",
    "inc a",
    "dec d",
    "jnz d -2",
    "dec c",
    "jnz c -5",
  ]
  assembunny_cpu = AssembunnyCpu(0, 0, 1, 0)
  assembunny_cpu.run(instructions)
  assert assembunny_cpu.get_register_value("a") == 9227737

def assert_register_values(assembunny_cpu: AssembunnyCpu, a:int, b:int, c:int, d:int):
  assert assembunny_cpu.get_register_value("a") == a
  assert assembunny_cpu.get_register_value("b") == b
  assert assembunny_cpu.get_register_value("c") == c
  assert assembunny_cpu.get_register_value("d") == d
