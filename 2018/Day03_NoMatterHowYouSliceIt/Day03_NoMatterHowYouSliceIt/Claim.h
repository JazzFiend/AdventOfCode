//
//  Claim.h
//  Day03_NoMatterHowYouSliceIt
//
//  Created by Phil Deitz on 5/17/19.
//  Copyright © 2019 Phil Deitz. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface Claim : NSObject

@property (readonly) NSString* id;
@property (readonly) NSInteger x;
@property (readonly) NSInteger y;
@property (readonly) NSInteger width;
@property (readonly) NSInteger height;

- (instancetype)initWithID:(NSString*) id
        andWithXCoordinate:(NSInteger) x
        andWithYCoordinate:(NSInteger) y
              andWithWidth:(NSInteger) width
             andWithHeight:(NSInteger) height;

@end

NS_ASSUME_NONNULL_END
