package com.sd.lib.umeng_push;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;

import org.android.agoo.xiaomi.MiPushRegistar;

public class LibUmengPush {
    private LibUmengPush() {
    }

    public static void init(Context context) {
        final Resources resources = context.getResources();

        final String xiaomiAppId = resources.getString(R.string.lib_umeng_push_xiaomi_app_id);
        final String xiaomiAppKey = resources.getString(R.string.lib_umeng_push_xiaomi_app_key);
        if (!TextUtils.isEmpty(xiaomiAppId) && !TextUtils.isEmpty(xiaomiAppKey)) {
            MiPushRegistar.register(context, xiaomiAppId, xiaomiAppKey);
        }
    }
}
