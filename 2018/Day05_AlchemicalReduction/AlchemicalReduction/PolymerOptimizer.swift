import Foundation

class PolymerOptimizer {
    public static func reducedLengthOfOptimized(polymer: String) -> Int {
        let alphabet = "abcdefghijklmnopqrstuvwxyz"
        var lowestLength = Int.max
        for letter in alphabet {
            let optimizedPolymer = polymer.replacingOccurrences(of: letter.lowercased(), with: "").replacingOccurrences(of: letter.uppercased(), with: "")
            let length = PolymerReducer.lengthAfterReduce(polymer: optimizedPolymer)
            if(lowestLength > length) {
                lowestLength = length
            }
        }
        return lowestLength
    }
}
