package com.sd.lib.umeng_push

import android.content.Context
import com.umeng.commonsdk.utils.UMUtils
import com.umeng.message.PushAgent
import com.umeng.message.api.UPushRegisterCallback

object LibUmengPush {
    @JvmStatic
    fun register(context: Context, callback: UPushRegisterCallback?) {
        if (UMUtils.isMainProgress(context)) {
            PushAgent.getInstance(context).register(callback)
        }
    }
}