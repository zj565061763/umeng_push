package com.sd.lib.umeng_push;

import android.content.Context;

import com.umeng.commonsdk.utils.UMUtils;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

public class LibUmengPush {
    private LibUmengPush() {
    }

    public static void preRegister(Context context) {
        if (!UMUtils.isMainProgress(context)) {
            PushAgent.getInstance(context).register(null);
        }
    }

    public static void register(Context context, IUmengRegisterCallback callback) {
        PushAgent.getInstance(context).register(callback);
    }
}
