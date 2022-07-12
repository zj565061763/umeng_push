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
import com.umeng.message.api.UPushRegisterCallback

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.i(MainActivity.TAG, "Application onCreate ${UMFrUtils.getCurrentProcessName(this)}")

        // 日志开关
        UMConfigure.setLogEnabled(true)
        // 预初始化
        LibUmengCommon.preInit(this, "60d28f6326a57f101832de50")

        if (isAgreementAccepted) {
            register(this)
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {
        /** 是否已经同意协议 */
        var isAgreementAccepted = false

        /**
         * 正式注册，要在同意协议之后才可以调用
         */
        fun register(context: Context) {
            check(isAgreementAccepted) { "You should accept agreement before this" }
            Log.i(MainActivity.TAG, "register")

            LibUmengCommon.init(context, "be29515ba1416294e6103410bb1eaad3")
            LibUmengPush.register(context, object : UPushRegisterCallback {
                override fun onSuccess(deviceToken: String) {
                    Log.i(MainActivity.TAG, "register onSuccess $deviceToken")
                }

                override fun onFailure(code: String, desc: String) {
                    Log.e(MainActivity.TAG, "register onFailure $code  $desc")
                }
            })
        }
    }
}