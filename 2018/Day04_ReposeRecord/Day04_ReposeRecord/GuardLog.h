

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface GuardLog : NSObject

- (instancetype) initWithGuardEntries:(NSArray<NSString*>*) guardEntries;
- (NSString*) toString;
- (NSInteger) computeGuardMinuteProduct;
- (NSInteger) computeGuardMostSleptMinuteProduct;

@end

NS_ASSUME_NONNULL_END
