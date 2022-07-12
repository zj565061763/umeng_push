package com.sd.lib.umeng_push;

import android.content.Context;

import com.umeng.commonsdk.utils.UMUtils;
import com.umeng.message.PushAgent;
import com.umeng.message.api.UPushRegisterCallback;

public final class LibUmengPush {
    /**
     * 注册
     */
    public static void register(Context context, UPushRegisterCallback callback) {
        if (UMUtils.isMainProgress(context)) {
            PushAgent.getInstance(context).register(callback);
        }
    }
}
