#import "GuardSleepCalculator.h"
#import "NightEntry.h"

@implementation GuardSleepCalculator

NSMutableArray<NSNumber*>* minuteTally;

+ (NSString*) findSleepiestGuard: (NSArray<NightEntry*>*) nightEntries {
    NSDictionary<NSString*, NSNumber*>* totalAsleepMinutesPerGuard = [self calculateTotalAsleepMinutesPerGuard: nightEntries];
    NSArray<NSString*>* sortedKeys = [self sortKeys: totalAsleepMinutesPerGuard];
    return sortedKeys[[sortedKeys count] - 1];
}

+ (NSDictionary<NSString*, NSNumber*>*) calculateTotalAsleepMinutesPerGuard: (NSArray<NightEntry*>*) nightEntries {
    NSMutableDictionary<NSString*, NSNumber*>* totalAsleepMinutesPerGuard = [[NSMutableDictionary alloc] init];
    for(NightEntry* n in nightEntries) {
        NSString* guard = [n getGuardId];
        if(totalAsleepMinutesPerGuard[guard] != nil) {
            NSNumber* updatedMinutes = [self addToNSNumberWithTerm1: totalAsleepMinutesPerGuard[guard]
                                                           andTerm2: [n calculateSleepMinutes]];
            [totalAsleepMinutesPerGuard setValue:updatedMinutes forKey:guard];
        } else {
            NSInteger sleepMinutes = [n calculateSleepMinutes];
            [totalAsleepMinutesPerGuard setValue:[[NSNumber alloc] initWithLong:(sleepMinutes)] forKey:guard];
        }
    }
    return (NSDictionary<NSString*, NSNumber*>*) totalAsleepMinutesPerGuard;
}

+ (NSArray<NSString*>*) sortKeys: (NSDictionary<NSString*, NSNumber*>*) asleepMinutesPerGuard {
    NSArray<NSString*>* sortedKeys = [asleepMinutesPerGuard keysSortedByValueUsingComparator:^(id obj1, id obj2) {
         if ([obj1 integerValue] > [obj2 integerValue]) {
              return (NSComparisonResult)NSOrderedDescending;
         }
         if ([obj1 integerValue] < [obj2 integerValue]) {
              return (NSComparisonResult)NSOrderedAscending;
         }
         return (NSComparisonResult)NSOrderedSame;
    }];
    return sortedKeys;
}

+ (NSNumber*) addToNSNumberWithTerm1: (NSNumber*) t1 andTerm2: (NSInteger) t2 {
    return [[NSNumber alloc] initWithLong:([t1 intValue] + t2)];
}

+ (NSInteger) findMostSleptMinuteWithGuardId:(NSString*) guardId andNights: (NSArray<NightEntry*>*) nightEntries {
    minuteTally = [[NSMutableArray alloc] initWithCapacity:60];
    [self initializeMinuteTally];
    [self incrementMinutesForGuardId:guardId andNightLogs:nightEntries];
    return [self findLargestElementInArray];
}

+ (void) initializeMinuteTally {
    for(NSInteger i = 0; i < 60; i++) {
        [minuteTally addObject:[[NSNumber alloc] initWithLong:0]];
    }
}

+ (void) incrementMinutesForGuardId: (NSString*) guardId andNightLogs: (NSArray<NightEntry*>*) nightEntries {
    for(NightEntry* n in nightEntries) {
        if([guardId isEqualToString: [n getGuardId]]) {
            for(NSInteger i = 0; i < [minuteTally count]; i++) {
                if(![n isGuardAwake:i]) {
                    NSNumber* incremented = [[NSNumber alloc] initWithLong:[minuteTally[i] longValue] + 1];
                    [minuteTally replaceObjectAtIndex:i withObject:incremented];
                }
            }
        }
    }
}

+ (NSInteger) findLargestElementInArray {
    NSInteger locationOfMax = -1;
    NSInteger max = -1;
    for(NSInteger i = 0; i < [minuteTally count]; i++) {
        if([minuteTally[i] longValue] > max) {
            max =[minuteTally[i] longValue];
            locationOfMax = i;
        }
    }
    return locationOfMax;
}

+ (NSMutableDictionary<NSString*, NSMutableArray<NSNumber*>*>*) populateGuardSleepTable: (NSMutableDictionary<NSString*, NightEntry*>*) allNightEntries {
    NSMutableDictionary<NSString*, NSMutableArray<NSNumber*>*>* guardSleepTable = [[NSMutableDictionary alloc] init];

    for(NSString* date in [allNightEntries allKeys]) {
        NightEntry* n = allNightEntries[date];
        NSString* guard = [n getGuardId];
        if(guardSleepTable[guard] == nil) {
            NSMutableArray<NSNumber*>* a = [[NSMutableArray alloc] initWithCapacity:60];
            for(NSInteger i = 0; i < 60; i++) {
                a[i] = [[NSNumber alloc] initWithInt:0];
            }
            [guardSleepTable setValue:a forKey:(guard)];
        }

        for(NSInteger i = 0; i < 60; i++) {
            if(![n isGuardAwake:i]) {
                NSMutableArray* a = guardSleepTable[guard];
                NSNumber* t = a[i];
                NSInteger ti = ([t longValue] + 1);
                a[i] = [[NSNumber alloc] initWithLong:ti];
                [guardSleepTable setValue:a forKey:guard];
            }
        }
    }

    return guardSleepTable;
}

@end
