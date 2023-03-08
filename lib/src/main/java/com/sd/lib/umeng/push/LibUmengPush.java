package com.sd.lib.umeng.push;

import android.content.Context;

import com.umeng.message.PushAgent;
import com.umeng.message.api.UPushRegisterCallback;

public class LibUmengPush {
    private LibUmengPush() {
    }

    /**
     * 注册
     */
    public static void register(Context context, UPushRegisterCallback callback) {
        PushAgent.getInstance(context).register(callback);
    }
}
