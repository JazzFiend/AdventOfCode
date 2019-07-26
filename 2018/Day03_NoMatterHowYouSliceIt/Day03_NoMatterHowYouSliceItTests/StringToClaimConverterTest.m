//
//  StringToClaimConverterTest.m
//  Day03_NoMatterHowYouSliceItTests
//
//  Created by Phil Deitz on 5/17/19.
//  Copyright © 2019 Phil Deitz. All rights reserved.
//

#import <XCTest/XCTest.h>
#import "Claim.h"
#import "StringToClaimConverter.h"

@interface StringToClaimConverterTest : XCTestCase

@end

@implementation StringToClaimConverterTest

- (void)testSingleClaimOnlySingleDigits {
    NSString* claimString = @"#9 @ 1,3: 2x4";
    Claim* c = [StringToClaimConverter convertToClaim:claimString];
    XCTAssertTrue([c.id isEqualToString:@"9"]);
    XCTAssertEqual(c.x, 1);
    XCTAssertEqual(c.y, 3);
    XCTAssertEqual(c.width, 2);
    XCTAssertEqual(c.height, 4);
}

- (void)testSingleClaimMultipleDigits {
    NSString* claimString = @"#29 @ 31,103: 12x400";
    Claim* c = [StringToClaimConverter convertToClaim:claimString];
    XCTAssertTrue([c.id isEqualToString: @"29"]);
    XCTAssertEqual(c.x, 31);
    XCTAssertEqual(c.y, 103);
    XCTAssertEqual(c.width, 12);
    XCTAssertEqual(c.height, 400);
}

@end
