package com.hzsuoyi.aliyun_log_flutter_sdk

import android.content.Context
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** AliyunLogFlutterSdkPlugin */
class AliyunLogFlutterSdkPlugin : FlutterPlugin, MethodCallHandler {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel
    private var logClient: AliyunLog? = null
    private lateinit var context: Context

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "aliyun_log_flutter_sdk")
        channel.setMethodCallHandler(this)

        context = flutterPluginBinding.applicationContext;
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method == "config") {
            val args = call.arguments as List<String>
            logClient = AliyunLog(context, args[0], args[1], args[2], args[3], args[4])
            result.success(null)
        } else if (call.method == "create") {
            logClient?.create()
            result.success(null)
        } else if (call.method == "setTopic") {
            logClient?.setTopic(call.arguments as String)
            result.success(null)
        } else if (call.method == "addTag") {
            logClient?.addTag(call.arguments as Map<String, String>)
            result.success(null)
        } else if (call.method == "addLog") {
            logClient?.addLog(call.arguments as Map<String, String>)
            result.success(null)
        } else {
            result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }
}
