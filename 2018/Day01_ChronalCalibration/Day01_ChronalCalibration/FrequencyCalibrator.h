#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface FrequencyCalibrator : NSObject

+ (NSInteger) findResultingFrequency: NSString;
+ (NSInteger) calibrate: NSString;

@end

NS_ASSUME_NONNULL_END
