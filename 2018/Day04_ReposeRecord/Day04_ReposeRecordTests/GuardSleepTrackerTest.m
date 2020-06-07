#import <XCTest/XCTest.h>

#import "GuardLog.h"

@interface GuardSleepTrackerTest : XCTestCase

@end

@implementation GuardSleepTrackerTest

NSString* inputPathHeader = @"/Users/philipd/workspace/Personal/AdventOfCode/2018/Day04_ReposeRecord/Day04_ReposeRecordTests/Inputs/";
NSString* expectedPathHeader = @"/Users/philipd/workspace/Personal/AdventOfCode/2018/Day04_ReposeRecord/Day04_ReposeRecordTests/Expected/";

- (void)testOneEntry {
    NSString* inputPath = [inputPathHeader stringByAppendingString: @"testOneEntryInput.txt"];
    NSArray<NSString*>* guardSleepLog = [self getInputFromFile: inputPath];

    NSString* expectedPath = [expectedPathHeader stringByAppendingString: @"testOneEntryExpected.txt"];
    NSString* expected = [self getExpectedFromFile: expectedPath];

    GuardLog* n = [[GuardLog alloc] initWithGuardEntries:guardSleepLog];
    XCTAssertTrue([[n toString] isEqualToString:expected]);
}

- (void)testOneEntryReversed {
    NSString* inputPath = [inputPathHeader stringByAppendingString: @"testOneEntryReversedInput.txt"];
    NSArray<NSString*>* guardSleepLog = [self getInputFromFile: inputPath];

    NSString* expectedPath = [expectedPathHeader stringByAppendingString: @"testOneEntryExpected.txt"];
    NSString* expected = [self getExpectedFromFile: expectedPath];

    GuardLog* n = [[GuardLog alloc] initWithGuardEntries:guardSleepLog];
    XCTAssertTrue([[n toString] isEqualToString:expected]);
}

- (void)testTwoEntriesOneGuard {
    NSString* inputPath = [inputPathHeader stringByAppendingString: @"testTwoEntriesOneGuardInput.txt"];
    NSArray<NSString*>* guardSleepLog = [self getInputFromFile: inputPath];

    NSString* expectedPath = [expectedPathHeader stringByAppendingString: @"testTwoEntriesOneGuardExpected.txt"];
    NSString* expected = [self getExpectedFromFile: expectedPath];

    GuardLog* n = [[GuardLog alloc] initWithGuardEntries:guardSleepLog];
    XCTAssertTrue([[n toString] isEqualToString:expected]);
}

- (void)testManyEntriesTwoGuards {
    NSString* inputPath = [inputPathHeader stringByAppendingString: @"testManyEntriesTwoGuardsInput.txt"];
    NSArray<NSString*>* guardSleepLog = [self getInputFromFile: inputPath];

    NSString* expectedPath = [expectedPathHeader stringByAppendingString: @"testManyEntriesTwoGuardsExpected.txt"];
    NSString* expected = [self getExpectedFromFile: expectedPath];

    GuardLog* n = [[GuardLog alloc] initWithGuardEntries:guardSleepLog];
    XCTAssertTrue([[n toString] isEqualToString:expected]);
}

- (void)testOneEntryPreviousDayInput {
    NSString* inputPath = [inputPathHeader stringByAppendingString: @"testOneEntryPreviousDayInput.txt"];
    NSArray<NSString*>* guardSleepLog = [self getInputFromFile: inputPath];

    NSString* expectedPath = [expectedPathHeader stringByAppendingString: @"testOneEntryPreviousDayExpected.txt"];
    NSString* expected = [self getExpectedFromFile: expectedPath];

    GuardLog* n = [[GuardLog alloc] initWithGuardEntries:guardSleepLog];
    XCTAssertTrue([[n toString] isEqualToString:expected]);
}

- (void)testManyEntriesManyGuards {
    NSString* inputPath = [inputPathHeader stringByAppendingString: @"testManyEntriesManyGuardsInput.txt"];
    NSArray<NSString*>* guardSleepLog = [self getInputFromFile: inputPath];

    NSString* expectedPath = [expectedPathHeader stringByAppendingString: @"testManyEntriesManyGuardsExpected.txt"];
    NSString* expected = [self getExpectedFromFile: expectedPath];

    GuardLog* n = [[GuardLog alloc] initWithGuardEntries:guardSleepLog];
    XCTAssertTrue([[n toString] isEqualToString:expected]);
    XCTAssertTrue([n computeGuardMinuteProduct] == 240);
    XCTAssertTrue([n computeGuardMostSleptMinuteProduct] == 4455);
}

- (void)testGuardNoSleep {
    NSString* inputPath = [inputPathHeader stringByAppendingString: @"testGuardNoSleepInput.txt"];
    NSArray<NSString*>* guardSleepLog = [self getInputFromFile: inputPath];

    NSString* expectedPath = [expectedPathHeader stringByAppendingString: @"testGuardNoSleepExpected.txt"];
    NSString* expected = [self getExpectedFromFile: expectedPath];

    GuardLog* n = [[GuardLog alloc] initWithGuardEntries:guardSleepLog];
    XCTAssertTrue([[n toString] isEqualToString:expected]);
}

- (void)testPuzzle {
    NSString* inputPath = [inputPathHeader stringByAppendingString: @"PuzzleInput.txt"];
    NSArray<NSString*>* guardSleepLog = [self getInputFromFile: inputPath];

    GuardLog* n = [[GuardLog alloc] initWithGuardEntries:guardSleepLog];
    XCTAssertTrue([n computeGuardMinuteProduct] == 20859);
    NSInteger doink = [n computeGuardMostSleptMinuteProduct];
    XCTAssertTrue([n computeGuardMostSleptMinuteProduct] == 76576);
}

- (NSArray*) getInputFromFile: (NSString*) filePath {
    NSString* fileContents = [NSString stringWithContentsOfFile:filePath encoding:NSUTF8StringEncoding error:nil];
    NSMutableArray* nightLog = (NSMutableArray*)[fileContents componentsSeparatedByString:@"\n"];
    [nightLog removeLastObject];
    return (NSArray*)nightLog;
}

- (NSString*) getExpectedFromFile: (NSString*) expectedPath {
    NSString* expected = [NSString stringWithContentsOfFile:expectedPath encoding:NSUTF8StringEncoding error:nil];
    NSString* expectedWithoutCR = [expected substringToIndex:[expected length] - 1];
    return expectedWithoutCR;
}

@end
