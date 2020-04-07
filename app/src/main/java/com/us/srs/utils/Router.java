package com.us.srs.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;


public class Router {
    public static void starIntent(Context context, Class clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }

    public static void starIntent(Context context, Class clazz, String... paras) {
        Intent intent = new Intent(context, clazz);
        for (int i = 0; i < paras.length; ++i) {
            intent.putExtra(paras[i], paras[++i]);
        }
        context.startActivity(intent);
    }

    public static void starIntent(Context context, Class clazz, Intent intent) {
        intent.setClass(context, clazz);
        context.startActivity(intent);
    }

    public static void starIntentForResult(Activity activity, Class clazz, int requestCode) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivityForResult(intent, requestCode);
    }


    public static void starIntentForResult(Activity activity, Class clazz, int requestCode, String... paras) {
        Intent intent = new Intent(activity, clazz);
        for (int i = 0; i < paras.length; ++i) {
            intent.putExtra(paras[i], paras[++i]);
        }
        activity.startActivityForResult(intent, requestCode);
    }

    public static void starIntentForResult(Activity activity, Class clazz, int requestCode, Intent intent) {
        intent.setClass(activity, clazz);
        activity.startActivityForResult(intent, requestCode);
    }

}
