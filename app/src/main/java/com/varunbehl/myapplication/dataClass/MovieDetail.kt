package com.varunbehl.myapplication.dataClass


import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@SuppressLint("ParcelCreator")
class ResultsItem(@SerializedName("overview")
                  val overview: String = "",
                  @SerializedName("original_language")
                  val originalLanguage: String = "",
                  @SerializedName("original_title")
                  val originalTitle: String = "",
                  @SerializedName("video")
                  val video: Boolean = false,
                  @SerializedName("title")
                  val title: String = "",
                  @SerializedName("genre_ids")
                  val genreIds: List<Long>?,
                  @SerializedName("poster_path")
                  val posterPath: String = "",
                  @SerializedName("backdrop_path")
                  val backdropPath: String = "",
                  @SerializedName("release_date")
                  val releaseDate: String = "",
                  @SerializedName("popularity")
                  val popularity: Double = 0.0,
                  @SerializedName("vote_average")
                  val voteAverage: Double = 0.0,
                  @SerializedName("id")
                  val id: String,
                  @SerializedName("adult")
                  val adult: Boolean = false,
                  @SerializedName("vote_count")
                  val voteCount: Long = 0) : Parcelable


@Parcelize
@SuppressLint("ParcelCreator")
data class ProductionCountriesItem(@SerializedName("iso_3166_1")
                                   val iso: String = "",
                                   @SerializedName("name")
                                   val name: String = "") : Parcelable


@Parcelize
@SuppressLint("ParcelCreator")
data class Images(@SerializedName("backdrops")
                  val backdrops: List<BackdropsItem>?,
                  @SerializedName("posters")
                  val posters: List<PostersItem>?) : Parcelable


@Parcelize
@SuppressLint("ParcelCreator")
data class Similar(@SerializedName("page")
                   val page: Long = 0,
                   @SerializedName("total_pages")
                   val totalPages: Long = 0,
                   @SerializedName("results")
                   val results: List<ResultsItem>?,
                   @SerializedName("total_results")
                   val totalResults: Long = 0) : Parcelable


@Parcelize
@SuppressLint("ParcelCreator")
data class CrewItem(@SerializedName("gender")
                    val gender: Long = 0,
                    @SerializedName("credit_id")
                    val creditId: String = "",
                    @SerializedName("name")
                    val name: String = "",
                    @SerializedName("profile_path")
                    val profilePath: String = "",
                    @SerializedName("id")
                    val id: Long = 0,
                    @SerializedName("department")
                    val department: String = "",
                    @SerializedName("job")
                    val job: String = "") : Parcelable


@Parcelize
@SuppressLint("ParcelCreator")
data class ProductionCompaniesItem(@SerializedName("logo_path")
                                   val logoPath: String = "",
                                   @SerializedName("name")
                                   val name: String = "",
                                   @SerializedName("id")
                                   val id: Long = 0,
                                   @SerializedName("origin_country")
                                   val originCountry: String = "") : Parcelable


@Parcelize
@SuppressLint("ParcelCreator")
data class MovieDetail(@SerializedName("original_language")
                       val originalLanguage: String = "",
                       @SerializedName("imdb_id")
                       val imdbId: String = "",
                       @SerializedName("videos")
                       val videos: Videos,
                       @SerializedName("video")
                       val video: Boolean = false,
                       @SerializedName("title")
                       val title: String = "",
                       @SerializedName("recommendations")
                       val recommendations: Recommendations,
                       @SerializedName("backdrop_path")
                       val backdropPath: String = "",
                       @SerializedName("revenue")
                       val revenue: Long = 0,
                       @SerializedName("credits")
                       val credits: Credits,
                       @SerializedName("genres")
                       val genres: List<GenresItem>?,
                       @SerializedName("popularity")
                       val popularity: Double = 0.0,
                       @SerializedName("production_countries")
                       val productionCountries: List<ProductionCountriesItem>?,
                       @SerializedName("id")
                       val id: Long = 0,
                       @SerializedName("vote_count")
                       val voteCount: Long = 0,
                       @SerializedName("budget")
                       val budget: Long = 0,
                       @SerializedName("overview")
                       val overview: String = "",
                       @SerializedName("similar")
                       val similar: Similar,
                       @SerializedName("images")
                       val images: Images,
                       @SerializedName("original_title")
                       val originalTitle: String = "",
                       @SerializedName("runtime")
                       val runtime: Long = 0,
                       @SerializedName("poster_path")
                       val posterPath: String = "",
                       @SerializedName("spoken_languages")
                       val spokenLanguages: List<SpokenLanguagesItem>?,
                       @SerializedName("production_companies")
                       val productionCompanies: List<ProductionCompaniesItem>?,
                       @SerializedName("release_date")
                       val releaseDate: String = "",
                       @SerializedName("vote_average")
                       val voteAverage: Double = 0.0,
                       @SerializedName("belongs_to_collection")
                       val belongsToCollection: BelongsToCollection,
                       @SerializedName("tagline")
                       val tagline: String = "",
                       @SerializedName("adult")
                       val adult: Boolean = false,
                       @SerializedName("homepage")
                       val homepage: String = "",
                       @SerializedName("status")
                       val status: String = "") : Parcelable


@Parcelize
@SuppressLint("ParcelCreator")
data class Videos(@SerializedName("results")
                  val results: List<VideoItem>?) : Parcelable


@Parcelize
@SuppressLint("ParcelCreator")
data class Recommendations(@SerializedName("page")
                           val page: Long = 0,
                           @SerializedName("total_pages")
                           val totalPages: Long = 0,
                           @SerializedName("results")
                           val results: List<ResultsItem>?,
                           @SerializedName("total_results")
                           val totalResults: Long = 0) : Parcelable

@Parcelize
@SuppressLint("ParcelCreator")
data class Credits(@SerializedName("cast")
                   val cast: List<CastItem>?,
                   @SerializedName("crew")
                   val crew: List<CrewItem>?) : Parcelable


@Parcelize
@SuppressLint("ParcelCreator")
data class PostersItem(@SerializedName("aspect_ratio")
                       val aspectRatio: Double = 0.0,
                       @SerializedName("file_path")
                       val filePath: String = "",
                       @SerializedName("vote_average")
                       val voteAverage: Double = 0.0,
                       @SerializedName("width")
                       val width: Long = 0,
                       @SerializedName("iso_639_1")
                       val iso: String = "",
                       @SerializedName("vote_count")
                       val voteCount: Long = 0,
                       @SerializedName("height")
                       val height: Long = 0) : Parcelable


@Parcelize
@SuppressLint("ParcelCreator")
data class BelongsToCollection(@SerializedName("backdrop_path")
                               val backdropPath: String = "",
                               @SerializedName("name")
                               val name: String = "",
                               @SerializedName("id")
                               val id: Long = 0,
                               @SerializedName("poster_path")
                               val posterPath: String = "") : Parcelable


@Parcelize
@SuppressLint("ParcelCreator")
data class SpokenLanguagesItem(@SerializedName("name")
                               val name: String = "",
                               @SerializedName("iso_639_1")
                               val iso: String = "") : Parcelable


@Parcelize
@SuppressLint("ParcelCreator")
data class GenresItem(@SerializedName("name")
                      val name: String = "",
                      @SerializedName("id")
                      val id: Long = 0) : Parcelable


@Parcelize
@SuppressLint("ParcelCreator")
data class BackdropsItem(@SerializedName("aspect_ratio")
                         val aspectRatio: Double = 0.0,
                         @SerializedName("file_path")
                         val filePath: String = "",
                         @SerializedName("vote_average")
                         val voteAverage: Double = 0.0,
                         @SerializedName("width")
                         val width: Long = 0,
                         @SerializedName("vote_count")
                         val voteCount: Long = 0,
                         @SerializedName("height")
                         val height: Long = 0) : Parcelable

@Parcelize
@SuppressLint("ParcelCreator")
data class CastItem(@SerializedName("cast_id")
                    val castId: Long = 0,
                    @SerializedName("character")
                    val character: String = "",
                    @SerializedName("gender")
                    val gender: Long = 0,
                    @SerializedName("credit_id")
                    val creditId: String = "",
                    @SerializedName("name")
                    val name: String = "",
                    @SerializedName("profile_path")
                    val profilePath: String? = null,
                    @SerializedName("id")
                    val id: Long = 0,
                    @SerializedName("order")
                    val order: Long = 0) : Parcelable



@Parcelize
@SuppressLint("ParcelCreator")
data class VideoItem(val site: String = "",
                     val size: Long = 0,
                     val name: String = "",
                     val id: String = "",
                     val type: String = "",
                     val iso: String = "",
                     val key: String = ""): Parcelable