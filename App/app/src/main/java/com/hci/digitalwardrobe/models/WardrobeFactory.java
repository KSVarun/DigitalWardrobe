package com.hci.digitalwardrobe.models;

import android.content.Context;

import com.hci.digitalwardrobe.R;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WardrobeFactory {

    private static Context appContext;
    private static WardrobeFactory instance = null;
    private static Retrofit retrofit;
    private static String username;


    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        WardrobeFactory.username = username;
    }

    public static void init(Context context) {
        appContext = context;
        instance = new WardrobeFactory();

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder().client(okHttpClient)
                .baseUrl(context.getString(R.string.API_BASE_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static Context getAppContext() {
        return appContext;
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public static WardrobeFactory getInstance() {
        return instance == null ?
                (instance = new WardrobeFactory()):
                instance;
    }


}
