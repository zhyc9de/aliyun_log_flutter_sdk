# aliyun_log_flutter_sdk

Aliyun Log service producer For flutter.

Based on Native SDK
- [android 2.5.8](https://github.com/aliyun/aliyun-log-android-sdk/)
- [ios 2.2.8](https://github.com/aliyun/aliyun-log-ios-sdk/)
- default useHttps and setPersistent

## Usage

```dart
// set config first
await AliyunLogFlutterSdk.config('endpoint', 'project', 'logstore', 'accessKeyID', 'accessKeySecret');

// add tag if you need
await AliyunLogFlutterSdk.addTag({
    'uuid': device.uuid,
    'device': device.device,
    'system': device.system,
    'version': device.version,
  });

// create client, then you can add log
await AliyunLogFlutterSdk.create();

AliyunLogFlutterSdk.addLog('app_first_open');
```


