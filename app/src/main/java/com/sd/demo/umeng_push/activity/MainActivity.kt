package com.sd.demo.umeng_push.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import anet.channel.util.Utils.context
import com.sd.demo.umeng_push.R
import com.umeng.commonsdk.UMConfigure
import com.umeng.message.IUmengRegisterCallback
import com.umeng.message.PushAgent


class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUmengSDK()

        val pushAgent = PushAgent.getInstance(context)
        pushAgent.register(object : IUmengRegisterCallback {
            override fun onSuccess(deviceToken: String) {
                Log.i(TAG, "onSuccess ${deviceToken}")
            }

            override fun onFailure(code: String, desc: String) {
                Log.e(TAG, "onFailure ${code}  ${desc}")
            }
        })
    }

    private fun initUmengSDK() {
        UMConfigure.init(
            this, "60d28f6326a57f101832de50", "Umeng",
            UMConfigure.DEVICE_TYPE_PHONE,
            "be29515ba1416294e6103410bb1eaad3"
        )
    }
}