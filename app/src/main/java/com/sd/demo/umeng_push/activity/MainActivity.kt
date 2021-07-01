package com.sd.demo.umeng_push.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sd.demo.umeng_push.R
import com.sd.lib.umeng_push.LibUmengPush
import com.umeng.commonsdk.UMConfigure
import com.umeng.message.IUmengRegisterCallback
import com.umeng.message.PushAgent
import com.umeng.message.UmengMessageHandler
import com.umeng.message.UmengNotificationClickHandler
import com.umeng.message.entity.UMessage


class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUmengSDK()
    }

    private fun initUmengSDK() {
        UMConfigure.init(
                this,
                "60d28f6326a57f101832de50",
                "Umeng",
                UMConfigure.DEVICE_TYPE_PHONE,
                "be29515ba1416294e6103410bb1eaad3"
        )

        val pushAgent = PushAgent.getInstance(this)
        pushAgent.apply {
            this.notificationClickHandler = _notificationClickHandler
            this.messageHandler = _umengMessageHandler
        }

        LibUmengPush.register(this, object : IUmengRegisterCallback {
            override fun onSuccess(deviceToken: String) {
                Log.i(TAG, "onSuccess ${deviceToken}")
            }

            override fun onFailure(code: String, desc: String) {
                Log.e(TAG, "onFailure ${code}  ${desc}")
            }
        })
    }

    private val _umengMessageHandler = object : UmengMessageHandler() {
        override fun dealWithNotificationMessage(context: Context, msg: UMessage) {
            super.dealWithNotificationMessage(context, msg)
            Log.i(TAG, "dealWithNotificationMessage")
        }

        override fun dealWithCustomMessage(context: Context, msg: UMessage) {
            super.dealWithCustomMessage(context, msg)
            Log.i(TAG, "dealWithCustomMessage")
        }
    }

    private val _notificationClickHandler: UmengNotificationClickHandler = object : UmengNotificationClickHandler() {
        override fun launchApp(context: Context, msg: UMessage) {
            super.launchApp(context, msg)
            Log.i(TAG, "launchApp")
        }

        override fun openUrl(context: Context, msg: UMessage) {
            super.openUrl(context, msg)
            Log.i(TAG, "openUrl")
        }

        override fun openActivity(context: Context, msg: UMessage) {
            super.openActivity(context, msg)
            Log.i(TAG, "openActivity")
        }

        override fun dealWithCustomAction(context: Context, msg: UMessage) {
            Log.i(TAG, "dealWithCustomAction ${msg.custom}")
        }
    }
}