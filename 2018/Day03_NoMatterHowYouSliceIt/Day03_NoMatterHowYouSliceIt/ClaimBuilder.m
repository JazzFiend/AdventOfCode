//
//  ClaimBuilder.m
//  Day03_NoMatterHowYouSliceIt
//
//  Created by Phil Deitz on 5/23/19.
//  Copyright © 2019 Phil Deitz. All rights reserved.
//

#import "ClaimBuilder.h"
#import "Claim.h"

@implementation ClaimBuilder

- (Claim*) build {
    return [[Claim alloc] initWithID: self.id
                  andWithXCoordinate: self.x
                  andWithYCoordinate:(NSInteger) self.y
                  andWithWidth:(NSInteger) self.width
                  andWithHeight:(NSInteger) self.height];
}

@end
