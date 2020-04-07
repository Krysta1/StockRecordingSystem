package com.us.srs.db;
import android.content.Context;
import android.content.SharedPreferences;
public class SharedPreferencesUtil {

    public static void setGroupId( Context context,String id){
        SharedPreferences sp = context.getSharedPreferences("UserInfo", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Id",id);
        editor.commit();

    }


    public static String getGroupId(Context context) {
        SharedPreferences sp = context.getSharedPreferences("UserInfo", context.MODE_PRIVATE);
        return sp.getString("Id", "");
    }


}
