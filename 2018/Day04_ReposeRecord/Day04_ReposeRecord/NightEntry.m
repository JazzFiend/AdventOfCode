#import "NightEntry.h"
#import "SleepEventTracker.h"

NSString* const wakeChar = @".";
NSString* const sleepChar = @"#";

@implementation NightEntry {
    NSString* date;
    NSString* guardId;
    SleepEventTracker* sleepEvents;
    NSMutableArray<NSString*>* sleepStatusPerMinute;
}

- (instancetype) initWithDate:(NSString*) d {
    self = [super init];
    if (self) {
        date = d;
        guardId = @"";
        sleepEvents = [[SleepEventTracker alloc] init];
        sleepStatusPerMinute = [[NSMutableArray<NSString*> alloc] initWithCapacity:60];
        [self recalculateSleepStatus];
    }
    return self;
}

- (NSString*) getGuardId {
    return guardId;
}

- (void) setGuardId:(NSString*) g {
    guardId = g;
}

- (void) setFallAsleepMarker: (NSInteger) t {
    [sleepEvents addSleepStart:t];
    if([sleepEvents areSleepStatusesBalanced]) {
        [self recalculateSleepStatus];
    }
}

- (void) setWakeUpMarker: (NSInteger) t {
    [sleepEvents addSleepEnd:t];
    if([sleepEvents areSleepStatusesBalanced]) {
        [self recalculateSleepStatus];
    }
}

- (void) recalculateSleepStatus {
    NSString* sleepState = wakeChar;

    for(NSInteger i = 0; i < 60; i++) {
        if([sleepState isEqualToString:wakeChar]) {
            if([sleepEvents isSleepEvent:i]) {
                sleepState = sleepChar;
            }
        } else if([sleepState isEqualToString:sleepChar]) {
            if([sleepEvents isWakeEvent:i]) {
                sleepState = wakeChar;
            }
        }
        sleepStatusPerMinute[i] = sleepState;
    }
}

- (NSInteger) calculateSleepMinutes {
    NSInteger sleepMinutes = 0;
    for(NSInteger i = 0; i < 60; i++) {
        if([sleepStatusPerMinute[i] isEqualToString:sleepChar]) {
            sleepMinutes++;
        }
    }
    return sleepMinutes;
}

- (NSString*) toString {
    NSMutableString* nightAsString = [self buildToStringHeader];
    for(NSInteger i = 0; i < 60; i++) {
        [nightAsString appendString:sleepStatusPerMinute[i]];
    }
    return nightAsString;
}

- (NSMutableString*) buildToStringHeader {
    NSMutableString* header = [[NSMutableString alloc] initWithString:@""];
    [header appendString:date];
    [header appendString:@" "];
    [header appendString:guardId];
    [header appendString:@" "];
    return header;
}

- (BOOL) isGuardAwake:(NSInteger) minute {
    if([sleepStatusPerMinute[minute] isEqualToString:wakeChar]) {
        return YES;
    } else {
        return NO;
    }

    return [sleepEvents isSleepEvent:minute];
}

@end
