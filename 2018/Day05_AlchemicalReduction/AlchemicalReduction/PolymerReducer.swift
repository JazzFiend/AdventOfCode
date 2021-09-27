import Foundation

class PolymerReducer {
    public static func lengthAfterReduce(polymer: String) -> Int {
        var pointer = 0
        var inProgressPolymer = polymer
        while(pointer < inProgressPolymer.count - 1) {
            let first:String = String(inProgressPolymer[pointer])
            let second:String = String(inProgressPolymer[pointer + 1])

            if(isReactive(unit1: first, unit2: second)) {
                inProgressPolymer = destroy(polymer: inProgressPolymer, reactivePair: (first + second))
                if(pointer != 0) {
                    pointer -= 1
                }
            } else {
                pointer += 1
            }
        }
        return inProgressPolymer.count
    }

    private static func isReactive(unit1:String, unit2:String) -> Bool {
        return (unit1.lowercased() == unit2.lowercased() &&
                (unit1.isLowercase && unit2.isUppercase || unit2.isLowercase && unit1.isUppercase))
    }

    private static func destroy(polymer: String, reactivePair: String) -> String {
        return polymer.replacingOccurrences(of:reactivePair, with:"")
    }
}
