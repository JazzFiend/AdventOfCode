//
//  Claim.m
//  Day03_NoMatterHowYouSliceIt
//
//  Created by Phil Deitz on 5/17/19.
//  Copyright © 2019 Phil Deitz. All rights reserved.
//

#import "Claim.h"

@implementation Claim

- (instancetype)initWithID:(NSString*) id
        andWithXCoordinate:(NSInteger) x
        andWithYCoordinate:(NSInteger) y
              andWithWidth:(NSInteger) width
             andWithHeight:(NSInteger) height {
    self = [super init];
    if (self) {
        _id = id;
        _x = x;
        _y = y;
        _width = width;
        _height = height;
    }
    return self;
}

@end
