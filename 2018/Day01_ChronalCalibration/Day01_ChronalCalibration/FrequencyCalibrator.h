//
//  FrequencyCalibrator.h
//  Day01_ChronalCalibration
//
//  Created by Phil Deitz on 4/19/19.
//  Copyright © 2019 Phil Deitz. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface FrequencyCalibrator : NSObject

+ (NSInteger) findResultingFrequency: NSString;
+ (NSInteger) calibrate: NSString;

@end

NS_ASSUME_NONNULL_END
