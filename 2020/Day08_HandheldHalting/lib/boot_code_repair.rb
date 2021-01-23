require_relative 'processor'
require_relative './instruction/nop_instruction'
require_relative './instruction/jmp_instruction'
require_relative 'program_type'

class BootCodeRepair
  def self.repair_code(instruction_list)
    processor = Processor.new
    for i in 0..(instruction_list.length - 1)
      next unless instruction_list[i].replaceable?

      potential_fix = create_modified_program(instruction_list, i)
      processor.run(potential_fix)
      return processor.accumulator if processor.program_type == ProgramType.normal_exit
    end
    raise 'Unable to repair boot code'
  end

  def self.create_modified_program(instruction_list, i)
    replacement_instruction = instruction_list[i].replace
    potential_fix = instruction_list.clone
    potential_fix[i] = replacement_instruction
    potential_fix
  end
end
