package com.varunbehl.myapplication.network;

import com.varunbehl.myapplication.dataClass.Movie;
import com.varunbehl.myapplication.dataClass.MovieDetail;
import com.varunbehl.myapplication.dataClass.TvEntity;
import com.varunbehl.myapplication.dataClass.TvShowDetail;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by varunbehl on 07/03/17.
 */

public interface DataInterface {

    @GET("3/movie/{categories}")
    Observable<Movie> listMoviesInfo(@Path("categories") String categories, @Query("page") int page, @Query("api_key") String apiKey);


    @GET("3/movie/{id}/similar")
    Observable<Movie> getSimilarMovies(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("3/movie/{id}/recommendations")
    Observable<Movie> getRecommendedMovies(@Path("id") int id, @Query("api_key") String apiKey);

//
//    @GET("3/tv/{id}/videos")
//    Observable<Videos> listVideos(@Path("id") int id, @Query("api_key") String apiKey);
//
//    @GET("3/movie/{id}/reviews")
//    Observable<Reviews> listReviews(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("3/tv/{id}")
    Observable<TvEntity> listTvShows(@Path("id") String id, @Query("page") int page, @Query("api_key") String apiKey);


    @GET("3/tv/{id}")
    Observable<TvShowDetail> getTvShowDetail(@Path("id") int id, @Query("append_to_response") String credits, @Query("api_key") String apiKey);


    @GET("3/movie/{id}")
    Observable<MovieDetail> getMovieDetail(@Path("id") int id, @Query("api_key") String apiKey, @Query("append_to_response") String credits);

//
//    @GET("3/tv/{id}/season/{season}")
//    Observable<TvSeasonInfo> getTvSeasonInfo(@Path("id") String id, @Path("season") String season, @Query("api_key") String apiKey);

    @GET("3/tv/{id}/similar")
    Observable<TvEntity> getSimilarTvShows(@Path("id") String id, @Query("api_key") String apiKey);

    @GET("3/tv/{id}/recommendations")
    Observable<TvEntity> getRecommendedTvShows(@Path("id") String id, @Query("api_key") String apiKey);


//    @GET("3/tv/{id}/season/{season}/episode/{episodeId}")
//    Observable<EpisodeInfo> getEpisodeInfo(@Path("id") String id, @Path("season") String episodeId, @Path("episodeId") String season, @Query("api_key") String apiKey);
//
//    @GET("3/search/tv")
//    Observable<SearchResult> searchTvShows(@Query("api_key") String apiKey, @Query("query") String query);
//
//    @GET("3/person/{id}")
//    Observable<CastInfo> getCastInfo(@Path("id") String id, @Query("api_key") String apiKey, @Query("language") String language, @Query("append_to_response") String credits);
}
