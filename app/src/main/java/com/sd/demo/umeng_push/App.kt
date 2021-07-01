package com.sd.demo.umeng_push

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.umeng.commonsdk.UMConfigure

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        UMConfigure.setLogEnabled(true)
        UMConfigure.preInit(this, "60d28f6326a57f101832de50", "umeng")
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}