package com.sd.demo.umeng_push

import android.app.Application
import com.umeng.commonsdk.UMConfigure

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        UMConfigure.preInit(this, "60d28f6326a57f101832de50", "umeng")
    }
}