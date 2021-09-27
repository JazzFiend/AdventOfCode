import XCTest

import PolymerReducer

class AlchemicalReduction: XCTestCase {
    override func setUpWithError() throws {
        // Put setup code here. This method is called before the invocation of each test method in the class.
    }

    override func tearDownWithError() throws {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testemptyPolymer() throws {
        XCTAssertEqual(PolymerReducer.lengthAfterReduce(polymer: ""), 0)
    }

    func testOneUnit() throws {
        XCTAssertEqual(PolymerReducer.lengthAfterReduce(polymer: "a"), 1)
    }

    func testTwoUnitsNoReaction() throws {
        XCTAssertEqual(PolymerReducer.lengthAfterReduce(polymer: "aB"), 2)
    }

    func testTwoUnitsWithReaction() throws {
        XCTAssertEqual(PolymerReducer.lengthAfterReduce(polymer: "aA"), 0)
    }

    func testThreeUnitsOneReaction() throws {
        XCTAssertEqual(PolymerReducer.lengthAfterReduce(polymer: "bBa"), 1)
    }

    func testTwoIdentialReactions() throws {
        XCTAssertEqual(PolymerReducer.lengthAfterReduce(polymer: "rRrR"), 0)
    }

    func testTwoReactions() throws {
        XCTAssertEqual(PolymerReducer.lengthAfterReduce(polymer: "rRRr"), 0)
    }

    func testOneReactionLeadsToAnother() throws {
        XCTAssertEqual(PolymerReducer.lengthAfterReduce(polymer: "PdDp"), 0)
    }

    func testAcceptance() throws {
        XCTAssertEqual(PolymerReducer.lengthAfterReduce(polymer: "dabAcCaCBAcCcaDA"), 10)
    }

    func testPuzzlePartOne() throws {
        let fileName = "/Users/philipd/Workspace/Personal/AdventOfCode/2018/Day05_AlchemicalReduction/AlchemicalReduction/input.txt"
        let polymer:String = try! String(String(contentsOfFile: fileName).split(separator:"\n")[0])
        XCTAssertEqual(PolymerReducer.lengthAfterReduce(polymer: polymer), 11252)
    }
}
