#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface NightEntry : NSObject

- (instancetype) initWithDate:(NSString*) d;
- (NSString*) getGuardId;
- (void) setGuardId:(NSString*) g;
- (void) setFallAsleepMarker: (NSInteger) t;
- (void) setWakeUpMarker: (NSInteger) t;
- (NSInteger) calculateSleepMinutes;
- (NSString*) toString;
- (BOOL) isGuardAwake:(NSInteger) minute;

@end

NS_ASSUME_NONNULL_END
