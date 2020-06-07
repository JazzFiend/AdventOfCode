#import <Foundation/Foundation.h>

#import "NightEntry.h"

NS_ASSUME_NONNULL_BEGIN

@interface GuardSleepCalculator : NSObject

+ (NSString*) findSleepiestGuard: (NSArray<NightEntry*>*) nightLogs;
+ (NSInteger) findMostSleptMinuteWithGuardId: (NSString*) guard andNights: (NSArray<NightEntry*>*) nightLogs;
+ (NSMutableDictionary<NSString*, NSMutableArray<NSNumber*>*>*) populateGuardSleepTable: (NSMutableDictionary<NSString*, NightEntry*>*) allNightEntries;

@end

NS_ASSUME_NONNULL_END
