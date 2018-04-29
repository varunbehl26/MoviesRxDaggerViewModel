package com.varunbehl.myapplication.di.component;


import com.varunbehl.myapplication.MainActivity;
import com.varunbehl.myapplication.di.module.ApiModule;
import com.varunbehl.myapplication.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {
    void inject(MainActivity activity);

}
