#import "ChecksumCalculator.h"

@implementation ChecksumCalculator

NSInteger doubles;
NSInteger triples;

+ (NSInteger) calculateChecksum: (NSArray*)idList {
    doubles = 0;
    triples = 0;
    for (NSString* id in idList) {
        NSMutableDictionary<NSString*, NSNumber*>* characterOccuranceMap = [[NSMutableDictionary alloc] init];
        for(NSInteger i = 0; i < id.length; i++) {
            [ChecksumCalculator tallyCharacter:characterOccuranceMap withCharacter:[NSString stringWithFormat:@"%c", [id characterAtIndex:i]]];
        }
        [ChecksumCalculator countDoublesAndTriples:characterOccuranceMap];
    }
    return (doubles * triples);
}

+ (void) tallyCharacter: (NSMutableDictionary<NSString *,NSNumber *> *) characterOccuranceMap withCharacter:(NSString*) character {
    if(characterOccuranceMap[character] == nil) {
        characterOccuranceMap[character] = [NSNumber numberWithInt:1];
    } else {
        int value = [characterOccuranceMap[character] intValue];
        characterOccuranceMap[character] = [NSNumber numberWithInt:value + 1];
    }
}

+ (void) countDoublesAndTriples: (NSMutableDictionary<NSString *,NSNumber *> *) characterOccuranceMap {
    NSArray<NSString*>* keys = [characterOccuranceMap allKeys];
    bool doubleFlag = false;
    bool tripleFlag = false;
    for(NSString* key in keys) {
        if(!doubleFlag && [characterOccuranceMap[key] intValue] == 2) {
            doubles++;
            doubleFlag = true;
        } else if(!tripleFlag && [characterOccuranceMap[key] intValue] == 3) {
            triples++;
            tripleFlag = true;
        }
    }
}

@end
