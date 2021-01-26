import 'dart:async';

import 'package:aliyun_log_flutter_sdk/aliyun_log_flutter_sdk.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';

  @override
  void initState() {
    super.initState();
    addLog();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> addLog() async {
    await AliyunLogFlutterSdk.config('', '', '', '', '');
    await AliyunLogFlutterSdk.create();

    Timer.periodic(new Duration(seconds: 3), (timer) async {
      print(await AliyunLogFlutterSdk.addLog('test'));
      print(await AliyunLogFlutterSdk.addLog('test1', {
        "test": "gogogo",
      }));
      print(await AliyunLogFlutterSdk.addLog('test2', {
        "test": ["key", "value"].toString(),
        "test2": "ssss",
      }));
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('Running on: $_platformVersion\n'),
        ),
      ),
    );
  }
}
