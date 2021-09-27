import XCTest

class PolymerOptimizerTest: XCTestCase {
    func testExample() throws {
        let polymer = "dabAcCaCBAcCcaDA"
        let optimized = PolymerOptimizer.reducedLengthOfOptimized(polymer: polymer);
        XCTAssertEqual(optimized, 4)
    }

    func testPuzzlePartTwo() throws {
        let fileName = "/Users/philipd/Workspace/Personal/AdventOfCode/2018/Day05_AlchemicalReduction/AlchemicalReduction/input.txt"
        let polymer:String = try! String(String(contentsOfFile: fileName).split(separator:"\n")[0])
        let optimized = PolymerOptimizer.reducedLengthOfOptimized(polymer: polymer);
        XCTAssertEqual(optimized, 6118)
    }
}
