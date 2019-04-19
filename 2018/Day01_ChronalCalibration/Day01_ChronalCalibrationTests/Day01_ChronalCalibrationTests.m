//
//  Day01_ChronalCalibrationTests.m
//  Day01_ChronalCalibrationTests
//
//  Created by Phil Deitz on 4/19/19.
//  Copyright © 2019 Phil Deitz. All rights reserved.
//

#import <XCTest/XCTest.h>
#import "FrequencyCalibrator.h"

@interface Day01_ChronalCalibrationTests : XCTestCase

@end

@implementation Day01_ChronalCalibrationTests

- (void)setUp {
    // Put setup code here. This method is called before the invocation of each test method in the class.
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
}

- (void) testOneFrequencyChange {
    XCTAssertEqual([FrequencyCalibrator findResultingFrequency: @"+1"], 1);
}

- (void) testOneMultiDigitFrequencyChange {
    XCTAssertEqual([FrequencyCalibrator findResultingFrequency: @"+100"], 100);
}

- (void) testOneNegativeFrequencyChange {
    XCTAssertEqual([FrequencyCalibrator findResultingFrequency:@"-5"], -5);
}

- (void) testMultipleFrequencyChanges {
    XCTAssertEqual([FrequencyCalibrator findResultingFrequency:@"+1,-2,+3,+1"], 3);
    XCTAssertEqual([FrequencyCalibrator findResultingFrequency:@"+1,+1,+1"], 3);
    XCTAssertEqual([FrequencyCalibrator findResultingFrequency:@"+1,+1,-2"], 0);
    XCTAssertEqual([FrequencyCalibrator findResultingFrequency:@"-1,-2,-3"], -6);
}

- (void) testPartOne {
    NSString* fullPath = @"/Users/philipd/workspace/Personal/AdventOfCode/2018/Day01_ChronalCalibration/Day01_ChronalCalibrationTests/Input.txt";
    NSString* fileContents = [NSString stringWithContentsOfFile:fullPath encoding:NSUTF8StringEncoding error:nil];
    NSString* formattedFileContents = [fileContents stringByReplacingOccurrencesOfString:@"\n" withString:@","];

    XCTAssertEqual([FrequencyCalibrator findResultingFrequency:formattedFileContents], 439);
}

- (void) testCalibrateTwoIterations {
    XCTAssertEqual([FrequencyCalibrator calibrate:@"+1,-1"], 0);
}

- (void) testCalibrateWithOverflow {
    XCTAssertEqual([FrequencyCalibrator calibrate:@"+1,-2,+3,+1"], 2);
    XCTAssertEqual([FrequencyCalibrator calibrate:@"+3,+3,+4,-2,-4"], 10);
    XCTAssertEqual([FrequencyCalibrator calibrate:@"-6,+3,+8,+5,-6"], 5);
    XCTAssertEqual([FrequencyCalibrator calibrate:@"+7,+7,-2,-7,-4"], 14);
}

- (void) testPartTwo {
    NSString* fullPath = @"/Users/philipd/workspace/Personal/AdventOfCode/2018/Day01_ChronalCalibration/Day01_ChronalCalibrationTests/Input.txt";
    NSString* fileContents = [NSString stringWithContentsOfFile:fullPath encoding:NSUTF8StringEncoding error:nil];
    NSString* formattedFileContents = [fileContents stringByReplacingOccurrencesOfString:@"\n" withString:@","];

    XCTAssertEqual([FrequencyCalibrator calibrate:formattedFileContents], 124645);
}

@end
