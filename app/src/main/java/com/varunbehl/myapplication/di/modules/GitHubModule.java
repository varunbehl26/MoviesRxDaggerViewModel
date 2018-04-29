package com.varunbehl.myapplication.di.modules;


import com.varunbehl.myapplication.GitHubApiInterface;
import com.varunbehl.myapplication.di.scopes.UserScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class GitHubModule {

    @Provides
    @UserScope
    public GitHubApiInterface providesGitHubInterface(Retrofit retrofit) {
        return retrofit.create(GitHubApiInterface.class);
    }
}
