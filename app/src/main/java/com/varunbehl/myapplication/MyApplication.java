package com.varunbehl.myapplication;

import android.app.Application;

import com.varunbehl.myapplication.di.component.AppComponent;
import com.varunbehl.myapplication.di.component.DaggerAppComponent;
import com.varunbehl.myapplication.di.module.ApiModule;
import com.varunbehl.myapplication.di.module.AppModule;

public class MyApplication extends Application {
    private AppComponent mAppComponent;

    String API_BASE_URL = "http://api.themoviedb.org/";

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule(API_BASE_URL))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
