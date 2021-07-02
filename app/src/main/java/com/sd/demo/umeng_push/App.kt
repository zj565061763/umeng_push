package com.sd.demo.umeng_push

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.sd.lib.umeng_common.LibUmengCommon
import com.sd.lib.umeng_push.LibUmengPush
import com.umeng.commonsdk.UMConfigure

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        UMConfigure.setLogEnabled(true)
        LibUmengCommon.preInit(this)
        LibUmengPush.preRegister(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}