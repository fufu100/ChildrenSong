package com.foreverrich.childrensong.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by lyf on 2016/9/27.
 */

public class Dispatcher {
    private Context context;
    private Intent intent;
    private ActivityOptionsCompat options;
    private int requestCode = -1;

    private ServiceConnection serviceConn;
    private int mFlag;

    public Dispatcher(Context context){
        this.context = context;
        intent = new Intent();
    }
    public Dispatcher extra(String key, String value){
        intent.putExtra(key,value);
        return this;
    }
    public Dispatcher extra(String key, String[] value){
        intent.putExtra(key,value);
        return this;
    }

    public Dispatcher extra(String key, int value){
        intent.putExtra(key,value);
        return this;
    }

    public Dispatcher extra(String key, long value){
        intent.putExtra(key,value);
        return this;
    }

    public Dispatcher extra(String key, boolean value){
        intent.putExtra(key,value);
        return this;
    }

    public Dispatcher extra(Bundle bundle){
        intent.putExtras(bundle);
        return this;
    }

    public Dispatcher extra(String key, Parcelable value){
        intent.putExtra(key,value);
        return this;
    }

    public Dispatcher extra(String key, ArrayList<? extends Parcelable> list){
        intent.putParcelableArrayListExtra(key,list);
        return this;
    }

    public Dispatcher data(Uri uri){
        intent.setData(uri);
        return this;
    }

    public Dispatcher flag(int flag){
        intent.setFlags(flag);
        return this;
    }

    public Dispatcher requestCode(int code){
        this.requestCode = code;
        return this;
    }

//    public Dispatcher defaultAnimate(){
//        return animate(R.anim.right_slide_enter_anim,R.anim.left_slide_exit_anim);
//    }

    public Dispatcher animate(int enterResId,int exitResId){
        options = ActivityOptionsCompat.makeCustomAnimation(context,enterResId,exitResId);
        return this;
    }

    public Dispatcher animate(View transView, String transName){
        options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,transView,transName);
        return this;
    }


    public Dispatcher to(Class<?> classes){
        intent.setComponent(new ComponentName(context,classes));
        return this;
    }

    public Dispatcher action(String action){
        intent.setAction(action);
        return this;
    }

    public void go(){
        Bundle bundle = null;
        if (options != null) {
            bundle = options.toBundle();
        }
        if(context instanceof Activity) {
            ActivityCompat.startActivityForResult((Activity)context,intent,requestCode,bundle);
        }else {
            ContextCompat.startActivities(context,new Intent[]{intent},bundle);
        }
    }

    public void send(){
        context.sendBroadcast(intent);
    }

    public Dispatcher bind(@NonNull ServiceConnection connection, int flag){
        this.serviceConn = connection;
        this.mFlag = flag;
        return this;
    }

    public void start(){
        if(serviceConn != null){
            context.bindService(intent,serviceConn,mFlag);
        }else {
            context.startService(intent);
        }
    }
}
