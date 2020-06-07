#import "GuardLog.h"

#import "NightEntry.h"
#import "SleepEventTracker.h"
#import "GuardSleepCalculator.h"

@implementation GuardLog {
    NSMutableDictionary<NSString*, NightEntry*>* allNights;
}

- (instancetype) initWithGuardEntries:(NSArray<NSString*>*) logEntries {
    self = [super init];
    if (self) {
        allNights = [[NSMutableDictionary<NSString*, NightEntry*> alloc] init];
        for(NSString* logEntry in logEntries) {
            NSString* date = [self extractDate:logEntry];
            if(![allNights valueForKey:date]) {
                NightEntry* n = [[NightEntry alloc] initWithDate: date];
                [allNights setObject:n forKey:date];
            }
            [self parseLogEntry:logEntry withDate:date];
        }
    }
    return self;
}

- (void) parseLogEntry:(NSString*) logEntry withDate: date {
    if([self isBeginShift:logEntry]) {
        NightEntry* n = [allNights valueForKey:date];
        NSString* guardId = [self extractGuardId:logEntry];
        [n setGuardId: guardId];
        [allNights setObject:n forKey:date];
    } else if([self isFallAsleep:logEntry]) {
        NightEntry* n = [allNights valueForKey:date];
        NSString* sleepTimeStart = [logEntry substringWithRange:(NSMakeRange(15, 2))];
        [n setFallAsleepMarker: [sleepTimeStart intValue]];
        [allNights setObject:n forKey:date];
    } else if([self isWakeUp:logEntry]) {
        NightEntry* n = [allNights valueForKey:date];
        NSString* sleepTimeEnd =  [logEntry substringWithRange:(NSMakeRange(15, 2))];
        [n setWakeUpMarker: [sleepTimeEnd intValue]];
        [allNights setObject:n forKey:date];
    } else {
        // Should throw here or something
        return;
    }
}

- (NSString*) extractDate:(NSString*) beginShift {
    NSString* dateString = [beginShift substringWithRange:(NSMakeRange(1, 10))];
    NSDateFormatter* dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setDateFormat:@"yyyy-MM-dd"];
    NSDate* date = [dateFormatter dateFromString:dateString];
    if([self isPreviousDay:beginShift]) {
        date = [date dateByAddingTimeInterval:86400];
    }
    return [dateFormatter stringFromDate:date];
}

- (BOOL) isPreviousDay:(NSString*) beginShift {
    return [beginShift characterAtIndex:12] == '2';
}

- (BOOL) isBeginShift: (NSString*) logEntry {
    return [logEntry characterAtIndex:19] == 'G';
}

- (NSString*) extractGuardId:(NSString*) beginShift {
    NSInteger indexOfNumberSign = [beginShift rangeOfString:@"#"].location;
    NSString* startWithGuardID = [beginShift substringFromIndex:indexOfNumberSign];
    NSInteger indexOfSpace = [startWithGuardID rangeOfString:@" "].location;
    return [startWithGuardID substringWithRange:(NSMakeRange(0, indexOfSpace))];
}

- (BOOL) isFallAsleep: (NSString*) logEntry {
    return [logEntry characterAtIndex:19] == 'f';
}

- (BOOL) isWakeUp: (NSString*) logEntry {
    return [logEntry characterAtIndex:19] == 'w';
}

- (NSString*) toString {
    NSString* s = @"";
    NSArray<NSString*>* sortedDates = [self sortByDate];

    for(NSString* date in sortedDates) {
        s = [s stringByAppendingString:[allNights[date] toString]];
        s = [s stringByAppendingString:@"\n"];
    }
    return [s substringToIndex:[s length] - 1];
}

- (NSArray<NSString*>*) sortByDate {
    return [[allNights allKeys] sortedArrayUsingComparator:^NSComparisonResult(NSString* _Nonnull obj1, NSString*  _Nonnull obj2) {
        return [obj1 compare: obj2];
    }];
}

- (NSInteger) computeGuardMinuteProduct {
    NSString* sleepiestGuard = [GuardSleepCalculator findSleepiestGuard: [allNights allValues]];
    NSInteger mostSleptMinute = [GuardSleepCalculator findMostSleptMinuteWithGuardId:sleepiestGuard andNights:[allNights allValues]];
    NSInteger sleepiestGuardNumber = [[sleepiestGuard substringFromIndex:1] integerValue];

    return sleepiestGuardNumber * mostSleptMinute;
}

@end
