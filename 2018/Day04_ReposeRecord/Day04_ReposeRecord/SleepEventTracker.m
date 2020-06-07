
#import "SleepEventTracker.h"

@implementation SleepEventTracker
{
    NSMutableArray<NSNumber*>* sleepTimes;
    NSMutableArray<NSNumber*>* wakeTimes;
}

- (instancetype) init {
    self = [super init];
    if (self) {
        sleepTimes = [[NSMutableArray<NSNumber*> alloc] init];
        wakeTimes = [[NSMutableArray<NSNumber*> alloc] init];
    }
    return self;
}

- (void) addSleepStart: (NSInteger) t {
    [sleepTimes addObject:[self convertIntToNumber: t]];
}

- (void) addSleepEnd: (NSInteger) t {
    [wakeTimes addObject:[self convertIntToNumber: t]];
}

- (BOOL) isSleepEvent: (NSInteger) t {
    return ([sleepTimes containsObject:[self convertIntToNumber: t]]);
}

- (BOOL) isWakeEvent: (NSInteger) t {
    return ([wakeTimes containsObject:[self convertIntToNumber: t]]);
}

- (NSNumber*) convertIntToNumber: (NSInteger) i {
    return [[NSNumber alloc] initWithLong:i];
}

- (BOOL) areSleepStatusesBalanced {
    return [sleepTimes count] == [wakeTimes count];
}

@end

