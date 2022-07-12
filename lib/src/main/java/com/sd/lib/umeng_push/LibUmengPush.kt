package com.sd.lib.umeng_push

import android.app.ActivityManager
import android.content.Context
import android.os.Process
import com.umeng.message.PushAgent
import com.umeng.message.api.UPushRegisterCallback

object LibUmengPush {
    @JvmStatic
    fun registerOtherProcess(context: Context) {
        val pushProcess = context.packageName + ":channel"
        if (context.getProcessName() == pushProcess) {
            PushAgent.getInstance(context).register(null)
        }
    }

    @JvmStatic
    fun registerMainProcess(context: Context, callback: UPushRegisterCallback?) {
        val mainProcess = context.packageName
        if (context.getProcessName() == mainProcess) {
            PushAgent.getInstance(context).register(callback)
        }
    }
}

private fun Context.getProcessName(): String {
    val service = getSystemService(Context.ACTIVITY_SERVICE)
    if (service !is ActivityManager) return ""

    val list = service.runningAppProcesses
    if (list.isNullOrEmpty()) return ""

    return list.find { it.pid == Process.myPid() }?.processName ?: ""
}