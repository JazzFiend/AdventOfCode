#import "InventoryManager.h"
#import "ChecksumCalculator.h"

@implementation InventoryManager
NSString* commonLetters;

+ (NSInteger) calculateChecksum: (NSArray*) idList {
    return [ChecksumCalculator calculateChecksum:idList];
}

+ (bool) hasOneMismatch: (NSString*) string1 withSecondString: (NSString*) string2 {
    NSUInteger stringLength = [string1 length];
    commonLetters = @"";
    bool foundOneMismatch = false;

    for(NSUInteger k = 0; k < stringLength; k++) {
        if([string1 characterAtIndex:k] == [string2 characterAtIndex:k]) {
            commonLetters = [commonLetters stringByAppendingString:[NSString stringWithFormat:@"%c", [string1 characterAtIndex:k]]];
        } else {
            if(foundOneMismatch) {
                return false;
            }
            foundOneMismatch = true;
        }
    }
    return foundOneMismatch;
}

+ (NSString*) findFabricBoxes: (NSArray*) idList {
    for(NSUInteger i = 0; i < [idList count]; i++) {
        for(NSUInteger j = i + 1; j < [idList count]; j++) {
            if([InventoryManager hasOneMismatch:idList[i] withSecondString: idList[j]]) {
                return commonLetters;
            }
        }
    }
    return @"";
}
@end
