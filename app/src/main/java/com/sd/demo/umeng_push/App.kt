package com.sd.demo.umeng_push

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.multidex.MultiDex
import com.sd.lib.umeng_common.LibUmengCommon
import com.sd.lib.umeng_push.LibUmengPush
import com.sd.lib.utils.extend.FVersionCodeChecker
import com.umeng.commonsdk.UMConfigure
import com.umeng.commonsdk.framework.UMFrUtils

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val shouldShowAgreement = shouldShowAgreement(this)
        Log.i("MyApplication", "onCreate:${UMFrUtils.getCurrentProcessName(this)} shouldShowAgreement:${shouldShowAgreement}")

        UMConfigure.setLogEnabled(true)
        if (shouldShowAgreement) {
            // 还未同意《用户协议》
            LibUmengCommon.preInit(this)
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
        const val USER_AGREEMENT = "user_agreement"

        /**
         * 是否需要显示用户协议
         */
        fun shouldShowAgreement(context: Context): Boolean {
            val result = FVersionCodeChecker(context).check(USER_AGREEMENT)
                    ?: return false

            return result.isUpgraded && result.oldVersion == 0L
        }

        /**
         * 接受用户协议
         */
        fun acceptAgreement(context: Context) {
            FVersionCodeChecker(context).check(App.USER_AGREEMENT)?.commit()
        }
    }
}