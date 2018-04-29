package com.varunbehl.myapplication;

import com.varunbehl.myapplication.entity.Picture.Picture_Detail;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public interface GitHubApiInterface {
    @GET("3/movie/{categories}")
    Observable<Picture_Detail> listMoviesInfo(@Path("categories") String categories, @Query("page") int page, @Query("api_key") String apiKey);



}