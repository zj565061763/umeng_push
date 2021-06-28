package com.sd.demo.umeng_push.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sd.demo.umeng_push.R
import com.umeng.commonsdk.UMConfigure

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUmengSDK()
    }

    private fun initUmengSDK() {
        UMConfigure.init(
            this, "60d28f6326a57f101832de50", "Umeng",
            UMConfigure.DEVICE_TYPE_PHONE,
            "be29515ba1416294e6103410bb1eaad3"
        )
    }
}