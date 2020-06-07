#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface SleepEventTracker : NSObject

- (instancetype) init;
- (void) addSleepStart: (NSInteger) t;
- (void) addSleepEnd: (NSInteger) t;
- (BOOL) isSleepEvent: (NSInteger) t;
- (BOOL) isWakeEvent: (NSInteger) t;
- (BOOL) areSleepStatusesBalanced;

@end

NS_ASSUME_NONNULL_END
