package com.varunbehl.myapplication.network;

import com.varunbehl.myapplication.entity.Picture.Picture_Detail;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by varunbehl on 07/03/17.
 */

public interface DataInterface {

    @GET("3/movie/{categories}")
    Observable<Picture_Detail> listMoviesInfo(@Path("categories") String categories, @Query("page") int page, @Query("api_key") String apiKey);


    @GET("3/movie/{id}/similar")
    Observable<Picture_Detail> getSimilarMovies(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("3/movie/{id}/recommendations")
    Observable<Picture_Detail> getRecommendedMovies(@Path("id") int id, @Query("api_key") String apiKey);

}
