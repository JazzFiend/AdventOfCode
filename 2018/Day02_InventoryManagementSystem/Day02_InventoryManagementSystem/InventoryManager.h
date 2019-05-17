//
//  InventoryManager.h
//  Day02_InventoryManagementSystem
//
//  Created by Phil Deitz on 5/9/19.
//  Copyright © 2019 Phil Deitz. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface InventoryManager : NSObject

+ (NSInteger) calculateChecksum: (NSArray*)idList;
+ (NSString*) findFabricBoxes: (NSArray*)idList;

@end

NS_ASSUME_NONNULL_END
