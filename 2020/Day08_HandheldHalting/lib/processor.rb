require 'set'

require_relative './program_type'

class Processor
  attr_reader :accumulator, :program_type

  def initialize
    initialize_members
  end

  def initialize_members
    @accumulator = 0
    @program_counter = 0
    @program_type = ProgramType.unknown
  end

  def run(instruction_list)
    initialize_members
    instructions_run = Set[]
    until instruction_run_before?(instructions_run) || finished_instructions?(instruction_list)
      instructions_run.add(@program_counter)
      instruction = instruction_list[@program_counter]
      @accumulator = instruction.execute(@accumulator)
      @program_counter = instruction.update_program_counter(@program_counter)
    end
    determine_end_type(instructions_run, instruction_list)
  end

  def instruction_run_before?(instructions_run)
    instructions_run.include?(@program_counter)
  end

  def finished_instructions?(instruction_list)
    instruction_list[@program_counter].nil?
  end

  def determine_end_type(instructions_run, instruction_list)
    @program_type = ProgramType.infinite_loop if instructions_run.include?(@program_counter)
    @program_type = ProgramType.normal_exit if instruction_list[@program_counter].nil?
  end
end
