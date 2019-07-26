//
//  FabricClaimOverlay.h
//  Day03_NoMatterHowYouSliceIt
//
//  Created by Phil Deitz on 5/24/19.
//  Copyright © 2019 Phil Deitz. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Claim.h"

NS_ASSUME_NONNULL_BEGIN

@interface FabricClaimOverlay : NSObject

- (instancetype)initWithFabricWidth:(NSInteger) w
                andWithFabricHeight:(NSInteger) h;

- (NSString*) toString;

- (void) assignClaim:(Claim*) claim;

- (NSInteger) countOverlaps;

- (bool) isClaimAlone:(Claim*) claim;

@end

NS_ASSUME_NONNULL_END
