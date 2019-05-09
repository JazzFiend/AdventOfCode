//
//  ChecksumCalculator.h
//  Day02_InventoryManagementSystem
//
//  Created by Phil Deitz on 4/19/19.
//  Copyright © 2019 Phil Deitz. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface ChecksumCalculator : NSObject
+ (NSInteger) calculateChecksum: (NSArray*)idList;
@end

NS_ASSUME_NONNULL_END
