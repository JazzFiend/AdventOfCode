//
//  FabricClaimOverlayTest.m
//  Day03_NoMatterHowYouSliceItTests
//
//  Created by Phil Deitz on 5/24/19.
//  Copyright © 2019 Phil Deitz. All rights reserved.
//

#import <XCTest/XCTest.h>
#import "FabricClaimOverlay.h"
#import "Claim.h"
#import "StringToClaimConverter.h"

@interface FabricClaimOverlayTest : XCTestCase

@end

@implementation FabricClaimOverlayTest

FabricClaimOverlay* fco;

- (void)setUp {
    fco = [[FabricClaimOverlay alloc] initWithFabricWidth:3 andWithFabricHeight:3];
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
}

- (void) testDisplay {
    NSString* s = [fco toString];
    XCTAssertTrue([s isEqualToString: @"0,0,0,\n0,0,0,\n0,0,0,\n"]);
}

- (void)testOneClaim {
    Claim* c = [StringToClaimConverter convertToClaim:@"#4 @ 1,1: 1x2"];
    [fco assignClaim:c];
    XCTAssertTrue([[fco toString] isEqualToString:@"0,0,0,\n0,4,0,\n0,4,0,\n"]);
}

- (void)testTwoUnrelatedClaims {
    Claim* c1 = [StringToClaimConverter convertToClaim:@"#10 @ 0,1: 1x2"];
    Claim* c2 = [StringToClaimConverter convertToClaim:@"#4 @ 2,0: 1x3"];
    [fco assignClaim:c1];
    [fco assignClaim:c2];
    XCTAssertTrue([[fco toString] isEqualToString:@"0,0,4,\n10,0,4,\n10,0,4,\n"]);
}

- (void)testTwoRelatedClaims {
    Claim* c1 = [StringToClaimConverter convertToClaim:@"#1 @ 0,0: 3x1"];
    Claim* c2 = [StringToClaimConverter convertToClaim:@"#42 @ 1,0: 1x3"];
    [fco assignClaim:c1];
    [fco assignClaim:c2];
    XCTAssertTrue([[fco toString] isEqualToString:@"1,X,1,\n0,42,0,\n0,42,0,\n"]);
}

- (void)testPartOneExample {
    FabricClaimOverlay* fcoEights = [[FabricClaimOverlay alloc] initWithFabricWidth:8 andWithFabricHeight:8];
    Claim* c1 = [StringToClaimConverter convertToClaim:@"#1 @ 1,3: 4x4"];
    Claim* c2 = [StringToClaimConverter convertToClaim:@"#2 @ 3,1: 4x4"];
    Claim* c3 = [StringToClaimConverter convertToClaim:@"#3 @ 5,5: 2x2"];
    [fcoEights assignClaim:c1];
    [fcoEights assignClaim:c2];
    [fcoEights assignClaim:c3];
    XCTAssertTrue([[fcoEights toString] isEqualToString:   @"0,0,0,0,0,0,0,0,\n0,0,0,2,2,2,2,0,\n0,0,0,2,2,2,2,0,\n0,1,1,X,X,2,2,0,\n0,1,1,X,X,2,2,0,\n0,1,1,1,1,3,3,0,\n0,1,1,1,1,3,3,0,\n0,0,0,0,0,0,0,0,\n"]);
    XCTAssertEqual([fcoEights countOverlaps], 4);
}

- (void)testPartOne {
    FabricClaimOverlay* fcoThousand = [[FabricClaimOverlay alloc] initWithFabricWidth:1000 andWithFabricHeight:1000];
    NSString* fullPath = @"/Users/philipd/workspace/Personal/AdventOfCode/2018/Day03_NoMatterHowYouSliceIt/Day03_NoMatterHowYouSliceItTests/Input.txt";
    NSString* fileContents = [NSString stringWithContentsOfFile:fullPath encoding:NSUTF8StringEncoding error:nil];
    NSArray* idArray = [fileContents componentsSeparatedByString:@"\n"];

    for (NSString* s in idArray) {
        if(![s isEqualToString:@""]) {
            [fcoThousand assignClaim: [StringToClaimConverter convertToClaim:s]];
        }
    }
    XCTAssertEqual([fcoThousand countOverlaps], 110389);
}

- (void) testIsClaimAlone {
    FabricClaimOverlay* fcoEights = [[FabricClaimOverlay alloc] initWithFabricWidth:8 andWithFabricHeight:8];
    Claim* c1 = [StringToClaimConverter convertToClaim:@"#1 @ 1,3: 4x4"];
    Claim* c2 = [StringToClaimConverter convertToClaim:@"#2 @ 3,1: 4x4"];
    Claim* c3 = [StringToClaimConverter convertToClaim:@"#3 @ 5,5: 2x2"];
    [fcoEights assignClaim:c1];
    [fcoEights assignClaim:c2];
    [fcoEights assignClaim:c3];

    XCTAssertFalse([fcoEights isClaimAlone:c1]);
    XCTAssertFalse([fcoEights isClaimAlone:c2]);
    XCTAssertTrue([fcoEights isClaimAlone:c3]);
}

- (void)testPartTwo {
    FabricClaimOverlay* fcoThousand = [[FabricClaimOverlay alloc] initWithFabricWidth:1000 andWithFabricHeight:1000];
    NSString* fullPath = @"/Users/philipd/workspace/Personal/AdventOfCode/2018/Day03_NoMatterHowYouSliceIt/Day03_NoMatterHowYouSliceItTests/Input.txt";
    NSString* fileContents = [NSString stringWithContentsOfFile:fullPath encoding:NSUTF8StringEncoding error:nil];
    NSArray* idArray = [fileContents componentsSeparatedByString:@"\n"];
    NSMutableArray* claimArray = [[NSMutableArray alloc] init];

    for(NSString* s in idArray) {
        if(![s isEqualToString:@""]) {
            [claimArray addObject:[StringToClaimConverter convertToClaim:s]];
        }
    }

    for (Claim* claim in claimArray) {
        [fcoThousand assignClaim: claim];
    }

    for (Claim* claim in claimArray) {
        if([claim.id isEqualToString:@"552"]) {
            XCTAssertTrue([fcoThousand isClaimAlone:claim]);
        } else {
            XCTAssertFalse([fcoThousand isClaimAlone:claim]);
        }
    }
}

@end
