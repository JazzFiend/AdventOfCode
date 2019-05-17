#import <XCTest/XCTest.h>
#import "InventoryManager.h"

@interface InventoryManagerTest : XCTestCase

@end

@implementation InventoryManagerTest

NSMutableArray* idList;

- (void)setUp {
    idList = [[NSMutableArray alloc] init];
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
}

- (void)testEmptyList {
    [idList addObject:@""];
    XCTAssertEqual([InventoryManager calculateChecksum:idList], 0);
}

- (void)testNoDoublesOrTriples {
    [idList addObject:@"abcdef"];
    XCTAssertEqual([InventoryManager calculateChecksum:idList], 0);
}

- (void)testOneDoubleOneTriple {
    [idList addObject:@"abbcde"];
    [idList addObject:@"abcccd"];
    XCTAssertEqual([InventoryManager calculateChecksum:idList], 1);
}

- (void)testMultipleOfEach {
    [idList addObject:@"abcdef"];
    [idList addObject:@"bababc"];
    [idList addObject:@"abbcde"];
    [idList addObject:@"abcccd"];
    [idList addObject:@"aabcdd"];
    [idList addObject:@"abcdee"];
    [idList addObject:@"ababab"];
    XCTAssertEqual([InventoryManager calculateChecksum:idList], 12);
}

- (void)testPartOne {
    NSString* fullPath = @"/Users/philipd/workspace/Personal/AdventOfCode/2018/Day02_InventoryManagementSystem/Day02_InventoryManagementSystemTests/Input.txt";
    NSString* fileContents = [NSString stringWithContentsOfFile:fullPath encoding:NSUTF8StringEncoding error:nil];
    NSArray* idArray = [fileContents componentsSeparatedByString:@"\n"];

    XCTAssertEqual([InventoryManager calculateChecksum:idArray], 8820);
}

- (void)testTwoItemListFindBox {
    [idList addObject:@"aa"];
    [idList addObject:@"ab"];
    XCTAssertTrue([[InventoryManager findFabricBoxes:idList] isEqual:(@"a")]);
}

- (void)testThreeItemListFindBox {
    [idList addObject:@"aa"];
    [idList addObject:@"bc"];
    [idList addObject:@"ab"];
    XCTAssertTrue([[InventoryManager findFabricBoxes:idList] isEqual:(@"a")]);
}

- (void)testExample {
    [idList addObject:@"abcde"];
    [idList addObject:@"fghij"];
    [idList addObject:@"klmno"];
    [idList addObject:@"pqrst"];
    [idList addObject:@"fguij"];
    [idList addObject:@"axcye"];
    [idList addObject:@"wvxyz"];

    XCTAssertTrue([[InventoryManager findFabricBoxes:idList] isEqual:(@"fgij")]);
}

- (void)testPartTwo {
    NSString* fullPath = @"/Users/philipd/workspace/Personal/AdventOfCode/2018/Day02_InventoryManagementSystem/Day02_InventoryManagementSystemTests/Input.txt";
    NSString* fileContents = [NSString stringWithContentsOfFile:fullPath encoding:NSUTF8StringEncoding error:nil];
    NSMutableArray* idArray = [NSMutableArray arrayWithArray:[fileContents componentsSeparatedByString:@"\n"]];

    [idArray removeLastObject];

    XCTAssertTrue([[InventoryManager findFabricBoxes:idArray] isEqual:(@"bpacnmglhizqygfsjixtkwudr")]);
}

@end
