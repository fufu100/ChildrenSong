package com.foreverrich.childrensong.utils;

import android.content.Context;

/**
 * Created by lyf on 2016/11/9.
 */

public class ActivityUtils {
    public static Dispatcher from(Context context){
        return new Dispatcher(context);
    }
}
