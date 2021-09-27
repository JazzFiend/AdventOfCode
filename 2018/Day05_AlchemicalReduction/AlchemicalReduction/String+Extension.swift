import Foundation

extension String {
   var isLowercase: Bool {
       return self == self.lowercased()
   }

   var isUppercase: Bool {
       return self == self.uppercased()
   }
}
