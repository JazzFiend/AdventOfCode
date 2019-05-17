#import "FrequencyCalibrator.h"

@interface FrequencyCalibrator()

+ (NSInteger) calculateSign:(NSString*)frequencyChanges;
+ (NSInteger) convertToInt:(NSString*)signedInteger;

@end

@implementation FrequencyCalibrator

NSInteger total;

+ (NSInteger) findResultingFrequency: (NSString*)frequencyChanges {
    total = 0;
    NSArray* frequencyList = [frequencyChanges componentsSeparatedByString:@","];
    for (NSString* frequencyChange in frequencyList) {
        if(![frequencyChange isEqualToString:@""]) {
            total += [FrequencyCalibrator convertToInt:frequencyChange] * [FrequencyCalibrator calculateSign:frequencyChange];
        }
    }
    return total;
}

+ (NSInteger) calibrate: (NSString*)frequencyChanges {
    total = 0;
    NSArray* frequencyList = [frequencyChanges componentsSeparatedByString:@","];
    NSMutableSet* frequencySet = [[NSMutableSet alloc] init];
    [frequencySet addObject:@(0)];

    while(1) {
        for (NSString* frequencyChange in frequencyList) {
            if(![frequencyChange isEqualToString:@""]) {
                total += [FrequencyCalibrator convertToInt:frequencyChange] * [FrequencyCalibrator calculateSign:frequencyChange];
                if([frequencySet containsObject:@(total)]) {
                    return total;
                } else {
                    [frequencySet addObject:@(total)];
                }
            }
        }
    }
}

+ (NSInteger) calculateSign: (NSString*) signedNumber {
    if([[signedNumber substringToIndex:1] isEqualToString:@"-"]) {
        return -1;
    } else {
        return 1;
    }
}

+ (NSInteger) convertToInt: (NSString*) signedInteger {
    NSNumberFormatter* nf = [[NSNumberFormatter alloc] init];
    nf.numberStyle = NSNumberFormatterDecimalStyle;
    return [[nf numberFromString:[signedInteger substringFromIndex:1]] intValue];
}
@end
