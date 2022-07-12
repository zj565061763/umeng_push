package com.sd.demo.umeng_push

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.multidex.MultiDex
import com.sd.demo.umeng_push.activity.MainActivity
import com.sd.lib.umeng_common.LibUmengCommon
import com.sd.lib.umeng_push.LibUmengPush
import com.umeng.commonsdk.UMConfigure
import com.umeng.commonsdk.framework.UMFrUtils

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.i(MainActivity.TAG, "Application onCreate ${UMFrUtils.getCurrentProcessName(this)}")

        UMConfigure.setLogEnabled(true)
        if (shouldShowAgreement) {
            // 还未同意《用户协议》
            LibUmengCommon.preInit(this, "60d28f6326a57f101832de50")
        } else {
            LibUmengPush.registerOtherProcess(this)
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun getSystemService(name: String): Any {
        Log.i("MyApplication", "getSystemService:${name}")
        return super.getSystemService(name)
    }

    companion object {
        val shouldShowAgreement = true
    }
}