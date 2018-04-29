package com.varunbehl.myapplication.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }


    @Provides //scope is not necessary for parameters stored within the module
    public Context context() {
        return getmApplication().getApplicationContext();
    }

    public Application getmApplication() {
        return mApplication;
    }
}
