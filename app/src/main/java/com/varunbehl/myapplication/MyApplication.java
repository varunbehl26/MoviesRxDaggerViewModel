package com.varunbehl.myapplication;

import android.app.Application;

import com.varunbehl.myapplication.di.components.DaggerGitHubComponent;
import com.varunbehl.myapplication.di.components.DaggerNetComponent;
import com.varunbehl.myapplication.di.components.GitHubComponent;
import com.varunbehl.myapplication.di.components.NetComponent;
import com.varunbehl.myapplication.di.modules.AppModule;
import com.varunbehl.myapplication.di.modules.GitHubModule;
import com.varunbehl.myapplication.di.modules.NetModule;

public class MyApplication extends Application {
    String API_BASE_URL = "http://api.themoviedb.org/";
    private NetComponent mNetComponent;
    private GitHubComponent mGitHubComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(API_BASE_URL))
                .build();

        mGitHubComponent = DaggerGitHubComponent.builder()
                .netComponent(mNetComponent)
                .gitHubModule(new GitHubModule())
                .build();
    }


    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public GitHubComponent getGitHubComponent() {
        return mGitHubComponent;
    }

}
