require_relative './waypoint_navigation'
require_relative './instruction_decoder/waypoint_instruction_decoder'

class EmergencyWaypointSystem
  def self.find_destination(instructions)
    ship = WaypointNavigation.new
    decoded_instructions = WaypointInstructionDecoder.decode_instructions(instructions)
    ship.navigate(decoded_instructions)
    ship.waypoint_navigation.location
  end
end
