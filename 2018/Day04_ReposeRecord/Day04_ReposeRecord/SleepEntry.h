
#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface SleepEventTracker : NSObject

- (instancetype) init;
- (void) addSleepStart: (NSInteger) n;
- (void) addSleepEnd: (NSInteger) n;
- (BOOL) isSleepingAtTime: (NSInteger) t;
- (BOOL) isAwakeAtTime: (NSInteger) t;
- (BOOL) areSleepStatusesBalanced;

@end

NS_ASSUME_NONNULL_END
