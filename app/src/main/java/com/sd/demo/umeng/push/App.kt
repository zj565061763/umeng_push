package com.sd.demo.umeng.push

import android.app.Application
import android.content.Context
import com.sd.lib.umeng.common.LibUmengCommon
import com.umeng.commonsdk.UMConfigure
import com.umeng.commonsdk.framework.UMFrUtils
import com.umeng.message.PushAgent
import com.umeng.message.api.UPushRegisterCallback

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        logMsg { "Application onCreate ${UMFrUtils.getCurrentProcessName(this)}" }

        // 日志开关
        UMConfigure.setLogEnabled(true)
        // 预初始化
        LibUmengCommon.preInit(this, "60d28f6326a57f101832de50")

        if (isAgreementAccepted) {
            register(this)
        }
    }

    companion object {
        /** 是否已经同意协议 */
        var isAgreementAccepted = false

        /**
         * 正式注册，要在同意协议之后才可以调用
         */
        fun register(context: Context) {
            check(isAgreementAccepted) { "You should accept agreement before this" }
            logMsg { "register" }

            LibUmengCommon.init("be29515ba1416294e6103410bb1eaad3")
            PushAgent.getInstance(context).register(object : UPushRegisterCallback {
                override fun onSuccess(deviceToken: String) {
                    logMsg { "register onSuccess $deviceToken" }
                }

                override fun onFailure(code: String, desc: String) {
                    logMsg { "register onFailure $code $desc" }
                }
            })
        }
    }
}