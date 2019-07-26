//
//  StringToClaimConverter.m
//  Day03_NoMatterHowYouSliceIt
//
//  Created by Phil Deitz on 5/17/19.
//  Copyright © 2019 Phil Deitz. All rights reserved.
//

#import "StringToClaimConverter.h"
#import "Claim.h"
#import "ClaimBuilder.h"

@implementation StringToClaimConverter

typedef NS_ENUM(NSInteger, StringPortion) {
    FromBegining,
    FromEnd
};

+ (Claim*) convertToClaim: (NSString*) claimString {
    NSArray<NSString*>* tokenizedClaim = [claimString componentsSeparatedByString:@" "];
    return [StringToClaimConverter buildClaim:tokenizedClaim];
}

+ (Claim*) buildClaim: (NSArray<NSString*>*) tokenizedClaim {
    ClaimBuilder* b = [ClaimBuilder alloc];
    b.id = [StringToClaimConverter extractId:tokenizedClaim[0]];
    b.x = [StringToClaimConverter extractXCoordinate:tokenizedClaim[2]];
    b.y = [StringToClaimConverter extractYCoordinate:tokenizedClaim[2]];
    b.width = [StringToClaimConverter extractWidth:tokenizedClaim[3]];
    b.height = [StringToClaimConverter extractHeight:tokenizedClaim[3]];
    return [b build];
}

+ (NSString*) extractId: (NSString*) unformattedID {
    return [unformattedID substringFromIndex:1];
}

+ (NSInteger) extractXCoordinate: (NSString*) unformattedCoordinates {
    return [StringToClaimConverter extractFromOrderedPair:unformattedCoordinates withStartingPoint:FromBegining withSeperator:@","];
}

+ (NSInteger) extractYCoordinate: (NSString*) unformattedCoordinates {
    return [StringToClaimConverter extractFromOrderedPair:unformattedCoordinates withStartingPoint:FromEnd withSeperator:@","];
}

+ (NSInteger) extractWidth: (NSString*) unformattedArea {
    return [StringToClaimConverter extractFromOrderedPair:unformattedArea withStartingPoint:FromBegining withSeperator:@"x"];
}

+ (NSInteger) extractHeight: (NSString*) unformattedArea {
    return [StringToClaimConverter extractFromOrderedPair:unformattedArea withStartingPoint:FromEnd withSeperator:@"x"];
}

+ (NSInteger) extractFromOrderedPair: (NSString*) pair withStartingPoint:(StringPortion) startingPoint withSeperator:(NSString*) seperator {
    NSRange r;
    NSInteger seperatorLocation = [pair rangeOfString:seperator].location;
    if(startingPoint == FromBegining) {
        r = NSMakeRange(0, seperatorLocation);
    } else {
        r = NSMakeRange(seperatorLocation + 1, pair.length - seperatorLocation - 1);
    }
    return [[pair substringWithRange:r] intValue];
}

@end
