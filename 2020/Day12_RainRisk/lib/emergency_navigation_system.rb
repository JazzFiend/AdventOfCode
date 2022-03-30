require_relative './ship_navigation'
require_relative './instruction_decoder/navigation_instruction_decoder'

class EmergencyNavigationSystem
  def self.find_destination(instructions)
    ship = ShipNavigation.new
    decoded_instructions = NavigationInstructionDecoder.decode_instructions(instructions)
    ship.navigate(decoded_instructions)
    ship.ship_location.location
  end
end
