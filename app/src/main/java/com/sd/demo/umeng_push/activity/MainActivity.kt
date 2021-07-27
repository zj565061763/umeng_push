package com.sd.demo.umeng_push.activity

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.sd.demo.umeng_push.App
import com.sd.demo.umeng_push.R
import com.sd.lib.umeng_common.LibUmengCommon
import com.sd.lib.umeng_push.LibUmengPush
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

        if (App.shouldShowAgreement(this)) {
            showAgreementDialog()
        } else {
            initUmengSDK()
        }
    }

    private fun showAgreementDialog() {
        val dialog = AlertDialog.Builder(this)
                .setMessage("是否同意用户协议和隐私政策")
                .setNegativeButton("拒绝", DialogInterface.OnClickListener { dialog, which ->
                    finish()
                }).setPositiveButton("同意", DialogInterface.OnClickListener { dialog, which ->
                    App.acceptAgreement(this)
                    initUmengSDK()
                }).create()

        dialog.setCancelable(false)
        dialog.show()
    }

    private fun initUmengSDK() {
        LibUmengCommon.init(this)

        val pushAgent = PushAgent.getInstance(this)
        pushAgent.apply {
            this.notificationClickHandler = _notificationClickHandler
            this.messageHandler = _umengMessageHandler
        }
        LibUmengPush.registerMainProcess(this, object : IUmengRegisterCallback {
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