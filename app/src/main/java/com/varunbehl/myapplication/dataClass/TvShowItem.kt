package com.varunbehl.myapplication.dataClass


data class TvEntity(
        var page: Int?, // 1
        var total_results: Int?, // 20060
        var total_pages: Int?, // 1003
        var results: List<TvShowItem?>?
) {

    data class TvShowItem(
            var original_name: String?, // The 100
            var genre_ids: List<Int?>?,
            var name: String?, // The 100
            var popularity: Double? = 0.0, // 54.849527
            var origin_country: List<String?>?,
            var vote_count: Int?, // 1098
            var first_air_date: String?, // 2014-03-19
            var backdrop_path: String?, // /qYTIuJJ7fIehicAt3bl0vW70Sq6.jpg
            var original_language: String?, // en
            var id: Int?, // 48866
            var vote_average: Double? = 0.0,
            var overview: String?, // Based on the books by Kass Morgan, this show takes place 100 years in the future, when the Earth has been abandoned due to radioactivity. The last surviving humans live on an ark orbiting the planet — but the ark won't last forever. So the repressive regime picks 100 expendable juvenile delinquents to send down to Earth to see if the planet is still habitable.
            var poster_path: String? // /fjJAcCmeSRQIpFttRX6NGdjnTIh.jpg
    )
}