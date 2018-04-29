package com.varunbehl.myapplication.di.components;

import com.varunbehl.myapplication.MainActivity;
import com.varunbehl.myapplication.di.modules.GitHubModule;
import com.varunbehl.myapplication.di.scopes.UserScope;

import dagger.Component;

@UserScope
@Component(dependencies = NetComponent.class, modules = GitHubModule.class)
public interface GitHubComponent {
    void inject(MainActivity activity);
}
