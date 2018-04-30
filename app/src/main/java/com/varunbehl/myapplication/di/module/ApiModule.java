package com.varunbehl.myapplication.di.module;


import android.app.Application;
import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.readystatesoftware.chuck.ChuckInterceptor;
import com.varunbehl.myapplication.network.DataInterface;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    String mBaseUrl;

    public ApiModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }


    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }


    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(final Context context) {
        File httpCacheDirectory = new File(context.getCacheDir(), "cache_file");

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(5, TimeUnit.SECONDS);

//            if (BuildConfig.DEBUG) {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        builder.addInterceptor(interceptor);
//            }

        builder.addInterceptor(new ChuckInterceptor(context));

        return builder.build();
//        return  builder.cache(new Cache(httpCacheDirectory, 10 * 1024 * 1024)) // 10 MB
//                .addInterceptor(new Interceptor() {
//                    @Override public Response intercept(Chain chain) throws IOException {
//                        Request request = chain.request();
//                        if (isNetworkAvailable(context)) {
//                            request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build();
//                        } else {
//                            request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();
//                        }
//                        return chain.proceed(request);
//                    }
//                })
//                .build();

    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
    }


    @Provides
    @Singleton
    public DataInterface providesGitHubInterface(Retrofit retrofit) {
        return retrofit.create(DataInterface.class);
    }

    public static boolean isNetworkAvailable(Context context) {
//        ConnectivityManager cm =
//                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        return activeNetwork != null &&
//                activeNetwork.isConnectedOrConnecting();
        return false;
    }
}
