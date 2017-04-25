package com.foreverrich.childrensong.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by lyf on 2016/2/1.
 */
public class ToastUtils {

    private static Toast myToast;

    public static void showToast(Context context, String msg) {

        if (null == myToast) {
            myToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            myToast.setText(msg);
        }

        myToast.show();
    }

    public static void showToast(Context context, int resId) {

        if (null == myToast) {
            myToast = Toast.makeText(context, context.getResources().getString(resId), Toast.LENGTH_SHORT);
        } else {
            myToast.setText(context.getResources().getString(resId));
        }
        myToast.show();
    }

    public static void showToastLong(Context context, String msg) {

        if (null == myToast) {
            myToast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        } else {
            myToast.setText(msg);
        }

        myToast.show();
    }

    public static void showToast(Context context, String msg, int duration) {

        if (null == myToast) {
            myToast = Toast.makeText(context, msg, duration);
        } else {
            myToast.setText(msg);
        }

        myToast.show();
    }

    public static void showToastCenter(Context context, String msg){
        if (null == myToast) {
            myToast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        } else {
            myToast.setText(msg);
        }

        myToast.setGravity(Gravity.CENTER, 0, 0);

        myToast.show();
    }

    public static void showSnackBar(View view, String msg, int actionRes, View.OnClickListener listener){
        if(view != null) {
            Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).setAction(actionRes,listener).show();
        }
    }

    public static void showSnackBar(View view, String msg){
        if(view != null) {
            Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
        }
    }

    public static void showSnackBar(View view, int msgRes){
        if(view != null) {
            Snackbar.make(view, msgRes, Snackbar.LENGTH_SHORT).show();
        }
    }
}
