package com.foreverrich.childrensong.Api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lyf on 2016/8/9.
 *
 */

public class Api {
    private String TAG = "Api";
    public static String BASE_URL = "http://ubestkid.com:8081";

    public final int time = 10;
    private Retrofit mRetrofit;
    private SongService mService;

    private Api() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        if (LogUtils.LOG_ON) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.interceptors().add(logging);
//        }
        builder.connectTimeout(time, TimeUnit.SECONDS);
        builder.readTimeout(time, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        mService = mRetrofit.create(SongService.class);
    }

    private static class SingletonHolder {
        private static final Api INSTANCE = new Api();
    }

    public static Api getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public SongService getGsService() {
        return mService;
    }

}
