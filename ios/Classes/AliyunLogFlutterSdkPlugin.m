#import "AliyunLogFlutterSdkPlugin.h"
#if __has_include(<aliyun_log_flutter_sdk/aliyun_log_flutter_sdk-Swift.h>)
#import <aliyun_log_flutter_sdk/aliyun_log_flutter_sdk-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "aliyun_log_flutter_sdk-Swift.h"
#endif

@implementation AliyunLogFlutterSdkPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftAliyunLogFlutterSdkPlugin registerWithRegistrar:registrar];
}
@end
