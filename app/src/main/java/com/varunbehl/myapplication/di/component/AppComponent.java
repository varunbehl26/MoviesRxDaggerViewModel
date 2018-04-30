package com.varunbehl.myapplication.di.component;


import com.varunbehl.myapplication.di.module.ApiModule;
import com.varunbehl.myapplication.di.module.AppModule;
import com.varunbehl.myapplication.ui.MainActivity;
import com.varunbehl.myapplication.ui.MovieDetailActivity;
import com.varunbehl.myapplication.ui.TvShowDetailActivity;
import com.varunbehl.myapplication.viewmodel.MovieDetailViewModel;
import com.varunbehl.myapplication.viewmodel.MainViewModel;
import com.varunbehl.myapplication.viewmodel.TvShowDetailViewModel;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
    void inject(MovieDetailActivity activity);
    void inject(MainViewModel mainViewModel);
    void inject(@NotNull MovieDetailViewModel datailViewModel);

    void inject(@NotNull TvShowDetailViewModel tvShowDetailViewModel);

    void inject(@NotNull TvShowDetailActivity tvShowDetailActivity);
}
