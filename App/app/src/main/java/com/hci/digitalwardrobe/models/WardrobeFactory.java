package com.hci.digitalwardrobe.models;

import android.content.Context;

import com.hci.digitalwardrobe.R;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

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
        retrofit = new Retrofit.Builder()
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
