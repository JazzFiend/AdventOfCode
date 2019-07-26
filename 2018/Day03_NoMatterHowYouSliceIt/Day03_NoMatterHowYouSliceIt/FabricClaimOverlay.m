//
//  FabricClaimOverlay.m
//  Day03_NoMatterHowYouSliceIt
//
//  Created by Phil Deitz on 5/24/19.
//  Copyright © 2019 Phil Deitz. All rights reserved.
//

#import "FabricClaimOverlay.h"
#import "Claim.h"

@implementation FabricClaimOverlay

NSInteger width;
NSInteger height;
NSMutableArray* fabric;

- (instancetype) initWithFabricWidth:(NSInteger) w
                andWithFabricHeight:(NSInteger) h {
    self = [super init];
    if (self) {
        width = w;
        height = h;
        fabric = [[NSMutableArray alloc] initWithCapacity:width];
        for(NSInteger i = 0; i < width; i++) {
            [fabric addObject:[[NSMutableArray alloc] initWithCapacity:height]];
            for(NSInteger j = 0; j < height; j++) {
                [fabric[i] addObject:@"0"];
            }
        }
    }
    return self;
}

- (NSString*) toString {
    NSString* s = @"";
    for(NSInteger i = 0; i < width; i++) {
        for(NSInteger j = 0; j < height; j++) {
           s = [s stringByAppendingString:fabric[i][j]];
           s = [s stringByAppendingString:@","];
        }
        s = [s stringByAppendingString:@"\n"];
    }
    return s;
}

- (void) assignClaim:(Claim*) claim {
    for(NSInteger i = claim.y; i < claim.y + claim.height; i++) {
        for(NSInteger j = claim.x; j < claim.x + claim.width; j++) {
            if([fabric[i][j] isEqualToString:@"0"]) {
                fabric[i][j] = claim.id;
            } else {
                fabric[i][j] = @"X";
            }
        }
    }
}

- (NSInteger) countOverlaps {
    NSInteger overlapCount = 0;
    for(NSInteger i = 0; i < width; i++) {
        for(NSInteger j = 0; j < height; j++) {
            if([fabric[i][j] isEqualToString:@"X"]) {
                overlapCount++;
            }
        }
    }
    return overlapCount;
}

- (bool) isClaimAlone:(Claim*) claim {
    for(NSInteger i = claim.y; i < claim.y + claim.height; i++) {
        for(NSInteger j = claim.x; j < claim.x + claim.width; j++) {
            if([fabric[i][j] isEqualToString:@"X"]) {
                return false;
            }
        }
    }
    return true;
}
@end
