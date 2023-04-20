package com.furkanekiz.rickandmorty;

import android.app.Activity;
import android.app.Application;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleObserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RickAndMortApp extends Application implements LifecycleObserver {
    private static final boolean DEBUG = true;
    private static final String TAG = RickAndMortApp.class.getSimpleName();
    public static RickAndMortApp rickAndMortApp;
    public static String packageName;

    public static RickAndMortyApi getApiService() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RickAndMortURI.getUrlApi()).addConverterFactory(GsonConverterFactory.create(gson)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        return retrofit.create(RickAndMortyApi.class);
    }

    @Override
    public void onCreate() {
        packageName = getPackageName();
        super.onCreate();
        rickAndMortApp = this;
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@androidx.annotation.NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                Log("onActivityCreated " + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityStarted(@androidx.annotation.NonNull Activity activity) {
                Log("onActivityStarted " + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityResumed(@androidx.annotation.NonNull Activity activity) {
                Log("onActivityResumed " + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityPaused(@androidx.annotation.NonNull Activity activity) {
                Log("onActivityPaused " + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityStopped(@androidx.annotation.NonNull Activity activity) {
                Log("onActivityStopped " + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivitySaveInstanceState(@androidx.annotation.NonNull Activity activity, @androidx.annotation.NonNull Bundle outState) {
                Log("onActivitySaveInstanceState " + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityDestroyed(@androidx.annotation.NonNull Activity activity) {
                Log("onActivityDestroyed " + activity.getClass().getSimpleName());
            }
        });

    }

    private static void Log(String message) {
        if (DEBUG) {
            Log.d(TAG, message);
        }
    }
}
