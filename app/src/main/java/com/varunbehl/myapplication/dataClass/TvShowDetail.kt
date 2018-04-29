package com.varunbehl.myapplication.dataClass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowDetail(
        var backdrop_path: String?, // /yp94aOXzuqcQHva90B3jxLfnOO9.jpg
        var created_by: List<CreatedBy?>?,
        var episode_run_time: List<Long?>?,
        var first_air_date: String?, // 2016-10-02
        var genres: List<GenresItem?>?,
        var homepage: String?, // http://www.hbo.com/westworld
        var id: Long?, // 63247
        var in_production: Boolean?, // true
        var languages: List<String?>?,
        var last_air_date: String?, // 2018-06-24
        var name: String?, // Westworld
        var networks: List<Network?>?,
        var number_of_episodes: Long?, // 20
        var number_of_seasons: Long?, // 2
        var origin_country: List<String?>?,
        var original_language: String?, // en
        var original_name: String?, // Westworld
        var overview: String?, // A dark odyssey about the dawn of artificial consciousness and the evolution of sin. Set at the Longersection of the near future and the reimagined past, it explores a world in which every human appetite, no matter how noble or depraved, can be indulged.
        var popularity: Double?, // 186.082943
        var poster_path: String?, // /6aj09UTMQNyfSfk0ZX8rYOEsXL2.jpg
        var production_companies: List<ProductionCompany?>?,
        var seasons: List<Season?>?,
        var status: String?, // Returning Series
        var type: String?, // Scripted
        var vote_average: Double?, // 8.1
        var vote_count: Long?, // 1423
        var credits: Credits?,
        var videos: Videos?,
        var images: Images?,
        var similar: Similar?,
        var recommendations: Recommendations?
) : Parcelable {
    @Parcelize
    data class Recommendations(
            var page: Long?, // 1
            var results: List<Result?>?,
            var total_pages: Long?, // 1
            var total_results: Long? // 20
    ) : Parcelable {
        @Parcelize
        data class Result(
                var backdrop_path: String?, // /p0BwOOwPf60ng1KOsusozOV0eyA.jpg
                var first_air_date: String?, // 2016-05-22
                var genre_ids: List<Long?>?,
                var id: Long?, // 64230
                var name: String?, // Preacher
                var origin_country: List<String?>?,
                var original_language: String?, // en
                var original_name: String?, // Preacher
                var overview: String?, // A preacher sets out on a mission to make the almighty himself confess his sin of abandoning the world. With his best friend Cassidy, an alcoholic Irish vampire, his love Tulip, a red blooded gun towing Texan, and the power of genesis, an unholy child born from an angel and a devil, Jesse gives up everything to set the world straight with its creator.
                var poster_path: String?, // /deL3cqkZ9nJ13q1YGHwu9mfqDBD.jpg
                var vote_average: Double?, // 7.4
                var vote_count: Long?, // 341
                var networks: List<Network?>?
        ) : Parcelable {

            @Parcelize
            data class Network(
                    var id: Long?, // 174
                    var logo: Logo?,
                    var name: String?, // AMC
                    var origin_country: String? // US
            ) : Parcelable {
                @Parcelize
                data class Logo(
                        var path: String?, // /pmvRmATOCaDykE6JrVoeYxlFHw3.png
                        var aspect_ratio: Double? // 1.768
                ) : Parcelable
            }
        }
    }


//    data class Credits(
//            var cast: List<Cast?>?,
//            var crew: List<Crew?>?
//    ) {
//
//        data class Cast(
//                var character: String?, // Elsie Hughes
//                var credit_id: String?, // 55a611a19251410996000c26
//                var gender: Long?, // 1
//                var id: Long?, // 82663
//                var name: String?, // Shannon Woodward
//                var order: Long?, // 19
//                var profile_path: String? // /gmDE4v65reDt6u2ReGekLZLRm5j.jpg
//        )
//
//
//        data class Crew(
//                var credit_id: String?, // 5ad6dcdb9251413b2d0085c7
//                var department: String?, // Sound
//                var gender: Long?, // 0
//                var id: Long?, // 1484972
//                var job: String?, // Music Editor
//                var name: String?, // Christopher Kaller
//                var profile_path: Any? // null
//        )
//    }


//    data class Videos(
//            var results: List<Result?>?
//    ) {
//
//        data class Result(
//                var id: String?, // 59fc47fec3a3681abb027e0f
//                var iso_639_1: String?, // en
//                var iso_3166_1: String?, // US
//                var key: String?, // JctIuZfSsa4
//                var name: String?, // Official Trailer
//                var site: String?, // YouTube
//                var size: Long?, // 720
//                var type: String? // Trailer
//        )
//    }

    @Parcelize
    data class Similar(
            var page: Long?, // 1
            var results: List<Result?>?,
            var total_pages: Long?, // 12
            var total_results: Long? // 233
    ) : Parcelable {
        @Parcelize
        data class Result(
                var backdrop_path: String?, // /144sKAEfXdM2VNrZzHuMSUr9zAa.jpg
                var first_air_date: String?, // 2011-11-06
                var genre_ids: List<Long?>?,
                var id: Long?, // 1401
                var original_language: String?, // en
                var original_name: String?, // Hell on Wheels
                var overview: String?, // Hell on Wheels tells the epic story of post-Civil War America, focusing on Cullen Bohannon, a Confederate soldier who sets out to exact revenge on the Union soldiers who killed his wife. His journey takes him west to Hell on Wheels, a dangerous, raucous, lawless melting pot of a town that travels with and services the construction of the first transcontinental railroad, an engineering feat unprecedented for its time.
                var origin_country: List<String?>?,
                var poster_path: String?, // /7tXBtP5jExj9gRztWmcfFbOCx25.jpg
                var popularity: Double?, // 14.305505
                var name: String?, // Hell on Wheels
                var vote_average: Double?, // 7.2
                var vote_count: Long? // 169
        ) : Parcelable
    }

    @Parcelize
    data class Network(
            var name: String?, // HBO
            var id: Long?, // 49
            var logo_path: String?, // /tuomPhY2UtuPTqqFnKMVHvSb724.png
            var origin_country: String? // US
    ) : Parcelable

    @Parcelize
    data class ProductionCompany(
            var id: Long?, // 103490
            var logo_path: String?="", // null
            var name: String?, // Kilter Films
            var origin_country: String?
    ) : Parcelable


//    data class Images(
//            var backdrops: List<Backdrop?>?,
//            var posters: List<Poster?>?
//    ) {
//
//        data class Backdrop(
//                var aspect_ratio: Double?, // 1.777777777777778
//                var file_path: String?, // /rUfC5ktNEBxmg5yICg6kPNqeyPD.jpg
//                var height: Long?, // 1080
//                var iso_639_1: String?, // en
//                var vote_average: Double?, // 0.0
//                var vote_count: Long?, // 0
//                var width: Long? // 1920
//        )
//
//
//        data class Poster(
//                var aspect_ratio: Double?, // 0.6748046875
//                var file_path: String?, // /5nSJUlBqZGBGkVKgheeznIvjLGx.jpg
//                var height: Long?, // 2048
//                var iso_639_1: String?, // bg
//                var vote_average: Double?, // 0.0
//                var vote_count: Long?, // 0
//                var width: Long? // 1382
//        )
//    }

//
//    data class Genre(
//            var id: Long?, // 878
//            var name: String? // Science Fiction
//    )

    @Parcelize
    data class CreatedBy(
            var id: Long?, // 1497967
            var name: String?, // Lisa Joy
            var gender: Long?, // 1
            var profile_path: String? // /gaHPV9066HYWytaOa0V04GJBOH.jpg
    ) : Parcelable

    @Parcelize
    data class Season(
            var air_date: String?, // 2018-04-22
            var episode_count: Long?, // 10
            var id: Long?, // 98895
            var name: String?, // Season Two: The Door
            var overview: String?, // The reckoning is here. After finding the center of The Maze, the hosts revolt against their human captors while searching for a new purpose: The Door.
            var poster_path: String?, // /xiwhkYtnvzXMb3iqItkTcbwqzt7.jpg
            var season_number: Long? // 2
    ) : Parcelable
}