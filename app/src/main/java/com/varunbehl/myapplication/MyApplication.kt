package com.varunbehl.myapplication

import android.app.Application
import com.bumptech.glide.request.target.ViewTarget
import com.varunbehl.myapplication.di.component.AppComponent
import com.varunbehl.myapplication.di.component.DaggerAppComponent
import com.varunbehl.myapplication.di.module.ApiModule
import com.varunbehl.myapplication.di.module.AppModule

class MyApplication : Application() {
    var appComponent: AppComponent? = null
        private set

    private var API_BASE_URL = "https://api.themoviedb.org/"

    override fun onCreate() {
        super.onCreate()
        ViewTarget.setTagId(R.id.glide_tag)
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .apiModule(ApiModule(API_BASE_URL))
                .build()

    }

}
