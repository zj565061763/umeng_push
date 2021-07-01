package com.sd.demo.umeng_push

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.sd.lib.umeng_push.LibUmengPush
import com.umeng.commonsdk.UMConfigure

class App : Application() {
    private val TAG = "App"

    override fun onCreate() {
        super.onCreate()
        UMConfigure.setLogEnabled(true)
        UMConfigure.preInit(this, "60d28f6326a57f101832de50", "umeng")
        LibUmengPush.preRegister(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}