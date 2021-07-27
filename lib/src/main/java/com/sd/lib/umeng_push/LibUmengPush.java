package com.sd.lib.umeng_push;

import android.content.Context;

import com.umeng.commonsdk.utils.UMUtils;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

public class LibUmengPush {
    private LibUmengPush() {
    }

    public static void registerOtherProcess(Context context) {
        if (!UMUtils.isMainProgress(context)) {
            PushAgent.getInstance(context).register(null);
        }
    }

    public static void registerMainProcess(Context context, IUmengRegisterCallback callback) {
        if (UMUtils.isMainProgress(context)) {
            PushAgent.getInstance(context).register(callback);
        }
    }
}
