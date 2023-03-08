package com.sd.demo.umeng.push

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.umeng.message.PushAgent
import com.umeng.message.UmengMessageHandler
import com.umeng.message.UmengNotificationClickHandler
import com.umeng.message.entity.UMessage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (App.isAgreementAccepted) {
            initUmengSDK()
        } else {
            showAgreementDialog()
        }
    }

    private fun showAgreementDialog() {
        val dialog = AlertDialog.Builder(this)
            .setMessage("是否同意用户协议和隐私政策")
            .setNegativeButton("拒绝") { _, _ ->
                finish()
            }.setPositiveButton("同意") { _, _ ->
                App.isAgreementAccepted = true
                initUmengSDK()
            }.create()

        dialog.setCancelable(false)
        dialog.show()
    }

    private fun initUmengSDK() {
        App.register(this)
        PushAgent.getInstance(this).apply {
            this.notificationClickHandler = _notificationClickHandler
            this.messageHandler = _umengMessageHandler
        }
    }

    private val _umengMessageHandler = object : UmengMessageHandler() {
        override fun dealWithNotificationMessage(context: Context, msg: UMessage) {
            super.dealWithNotificationMessage(context, msg)
            logMsg { "dealWithNotificationMessage" }
        }

        override fun dealWithCustomMessage(context: Context, msg: UMessage) {
            super.dealWithCustomMessage(context, msg)
            logMsg { "dealWithCustomMessage" }
        }
    }

    private val _notificationClickHandler: UmengNotificationClickHandler = object : UmengNotificationClickHandler() {
        override fun launchApp(context: Context, msg: UMessage) {
            super.launchApp(context, msg)
            logMsg { "launchApp" }
        }

        override fun openUrl(context: Context, msg: UMessage) {
            super.openUrl(context, msg)
            logMsg { "openUrl" }
        }

        override fun openActivity(context: Context, msg: UMessage) {
            super.openActivity(context, msg)
            logMsg { "openActivity" }
        }

        override fun dealWithCustomAction(context: Context, msg: UMessage) {
            logMsg { "dealWithCustomAction ${msg.custom}" }
        }
    }
}

inline fun logMsg(block: () -> String) {
    Log.i("umeng-push-demo", block())
}