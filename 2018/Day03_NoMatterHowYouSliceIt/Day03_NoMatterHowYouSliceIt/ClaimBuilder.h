//
//  ClaimBuilder.h
//  Day03_NoMatterHowYouSliceIt
//
//  Created by Phil Deitz on 5/23/19.
//  Copyright © 2019 Phil Deitz. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Claim.h"

NS_ASSUME_NONNULL_BEGIN

@interface ClaimBuilder : NSObject

@property NSString* id;
@property NSInteger x;
@property NSInteger y;
@property NSInteger width;
@property NSInteger height;

- (Claim*) build;

@end

NS_ASSUME_NONNULL_END
