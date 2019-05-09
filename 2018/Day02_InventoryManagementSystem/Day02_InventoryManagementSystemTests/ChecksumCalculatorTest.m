//
//  ChecksumCalculatorTest.m
//  Day02_InventoryManagementSystemTests
//
//  Created by Phil Deitz on 4/19/19.
//  Copyright © 2019 Phil Deitz. All rights reserved.
//

#import <XCTest/XCTest.h>
#import "ChecksumCalculator.h"

@interface ChecksumCalculatorTest : XCTestCase

@end

@implementation ChecksumCalculatorTest

NSMutableArray* idList;

- (void)setUp {
    idList = [[NSMutableArray alloc] init];
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
}

- (void)testEmptyList {
    [idList addObject:@""];
    XCTAssertEqual([ChecksumCalculator calculateChecksum:idList], 0);
}

- (void)testNoDoublesOrTriples {
    [idList addObject:@"abcdef"];
    XCTAssertEqual([ChecksumCalculator calculateChecksum:idList], 0);
}

- (void)testOneDoubleOneTriple {
    [idList addObject:@"abbcde"];
    [idList addObject:@"abcccd"];
    XCTAssertEqual([ChecksumCalculator calculateChecksum:idList], 1);
}

- (void)testMultipleOfEach {
    [idList addObject:@"abcdef"];
    [idList addObject:@"bababc"];
    [idList addObject:@"abbcde"];
    [idList addObject:@"abcccd"];
    [idList addObject:@"aabcdd"];
    [idList addObject:@"abcdee"];
    [idList addObject:@"ababab"];
    XCTAssertEqual([ChecksumCalculator calculateChecksum:idList], 12);
}

- (void)testPartOne {
    NSString* fullPath = @"/Users/philipd/workspace/Personal/AdventOfCode/2018/Day02_InventoryManagementSystem/Day02_InventoryManagementSystemTests/Input.txt";
    NSString* fileContents = [NSString stringWithContentsOfFile:fullPath encoding:NSUTF8StringEncoding error:nil];
    NSArray* idArray = [fileContents componentsSeparatedByString:@"\n"];

    XCTAssertEqual([ChecksumCalculator calculateChecksum:idArray], 8820);
}

@end
