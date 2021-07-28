package com.hzsuoyi.aliyun_log_flutter_sdk

import android.content.Context
import android.util.Log.e
import com.aliyun.sls.android.producer.Log
import com.aliyun.sls.android.producer.LogProducerClient
import com.aliyun.sls.android.producer.LogProducerConfig
import com.aliyun.sls.android.producer.LogProducerResult
import io.flutter.util.PathUtils.getFilesDir


class AliyunLog(context: Context, endpoint: String, project: String, logstore: String, accessKeyID: String, accessKeySecret: String) {
    private var mLogConfig: LogProducerConfig = LogProducerConfig(endpoint, project, logstore, accessKeyID, accessKeySecret)
    private var mLogClient: LogProducerClient? = null

    init {
        // 设置默认https
        mLogConfig.setUsingHttp(1)
        // 设置持久化日志
        mLogConfig.setPersistent(1);
        mLogConfig.setPersistentFilePath(getFilesDir(context) + "/log.dat");
        mLogConfig.setPersistentForceFlush(1);
        mLogConfig.setPersistentMaxFileCount(10);
        mLogConfig.setPersistentMaxFileSize(1024 * 1024);
        mLogConfig.setPersistentMaxLogCount(65536);
    }

    fun create() {
        mLogClient = LogProducerClient(mLogConfig) { resultCode, reqId, errorMessage, logBytes, compressedBytes ->
            android.util.Log.d("LogProducerCallback", String.format("%s %s %s %s %s",
                    LogProducerResult.fromInt(resultCode), reqId, errorMessage, logBytes, compressedBytes))
        }
    }

    fun setTopic(topic: String) {
        mLogConfig.setTopic(topic)
    }

    fun addTag(tags: Map<String, String>) {
        try {
            tags.forEach { (k, v) -> mLogConfig.addTag(k, v) }
        } catch (e: Throwable) {
            e("AliyunLog", "#addTag", e)
        }
    }

    fun addLog(tags: Map<String, String>): Boolean {
        return try {
            val log = Log()
            tags.forEach { (k, v) -> log.putContent(k, v) }
            mLogClient?.addLog(log, 0)?.isLogProducerResultOk!!
        } catch (e: Throwable) {
            e("AliyunLog", "#addLog", e)
            false
        }
    }
}
