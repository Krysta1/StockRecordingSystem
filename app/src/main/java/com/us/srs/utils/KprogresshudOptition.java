package com.us.srs.utils;

import android.content.Context;

import com.kaopiz.kprogresshud.KProgressHUD;


public class KprogresshudOptition {
    public static KProgressHUD getKProgressHUD(Context context) {
        KProgressHUD kProgressHUD = new KProgressHUD(context);
        kProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);
        return kProgressHUD;
    }
}
