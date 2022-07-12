package com.sd.lib.umeng_push;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;

import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.api.UPushRegisterCallback;

import java.util.List;

public class LibUmengPush {
    private LibUmengPush() {
    }

    public static void registerOtherProcess(Context context) {
        final String processName = getProcessName(context);
        final String pushProcess = context.getPackageName() + ":channel";
        if (TextUtils.equals(processName, pushProcess)) {
            PushAgent.getInstance(context).register(null);
        }
    }

    public static void registerMainProcess(Context context, UPushRegisterCallback callback) {
        final String processName = getProcessName(context);
        final String mainProcess = context.getPackageName();
        if (TextUtils.equals(processName, mainProcess)) {
            PushAgent.getInstance(context).register(callback);
        }
    }

    private static String getProcessName(Context context) {
        final ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningAppProcessInfo> list = manager.getRunningAppProcesses();
        if (list == null || list.isEmpty()) {
            return null;
        }

        final int pid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo item : list) {
            if (item.pid == pid) {
                return item.processName;
            }
        }
        return null;
    }
}
