//
//  StringToClaimConverter.h
//  Day03_NoMatterHowYouSliceIt
//
//  Created by Phil Deitz on 5/17/19.
//  Copyright © 2019 Phil Deitz. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Claim.h"

NS_ASSUME_NONNULL_BEGIN

@interface StringToClaimConverter : NSObject

+ (Claim*) convertToClaim: (NSString*) claimString;

@end

NS_ASSUME_NONNULL_END
