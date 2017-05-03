
package com.foreverrich.childrensong.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

public class LogUtils { 
	// 
	public static boolean LOG_ON = true ;
	public static boolean ONLINE_TEMPLATE = true;

	public static void DebugLog(String src) {
		if (LOG_ON)
			Log.d("儿歌", src);
	}

	public static void DebugLog(Exception e) {
		if (LOG_ON)
			Log.e("儿歌", "" + e);

	}
  
	public static void DebugLog(double double1) {
		if (LOG_ON)
			Log.d("儿歌", "" + double1);

	}

	public static void DebugLog(JSONObject put) {
		if (LOG_ON)
			Log.d("儿歌", "" + put);

	}

	public static void DebugLog(Object object) {
		if (LOG_ON)
			Log.d("儿歌", "" + object);

	}

	public static void DebugLog(String string, String string2) {
		if (LOG_ON)
			Log.d("儿歌", string + " " + string2);

	}

	public static void DebugLog(String logTag, String string, Exception e) {
		if (LOG_ON)
			Log.d("儿歌", logTag + " " + string + e.toString());
	}

	public static void ShowToast(Context context, String text) {
		if (context != null)
			Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
	public static void ShowToast(Context context, String text, int duration) {
		if (context != null)
			Toast.makeText(context, text, duration).show();
	}

	public static void ShowToast(Context context, int resid, int duration) {
		if (context != null)
			Toast.makeText(context, context.getString(resid), duration).show();

	}
	public static void ShowToast(Context context, int resid) {
		if (context != null)
			Toast.makeText(context, context.getString(resid), Toast.LENGTH_SHORT).show();

	}

	public static void d(String string, String string2) {
		if (LOG_ON)
			Log.d(string, string2);
	}

	public static void e(String string, String string2) {
		if (LOG_ON)
			Log.e(string, string2);
	}

	public static void w(String string, String string2) {
		if (LOG_ON)
			Log.w(string, string2);
	}

	public static void PST(Exception e) {
		if (LOG_ON) {
			e.printStackTrace();
		}
	}

	public static void PST(Throwable e) {
		if (LOG_ON) {
			e.printStackTrace();
		}
	}

	public static void PST(OutOfMemoryError e) {
		if (LOG_ON) {
			e.printStackTrace();
		}
	}


}
