package app.robusta.robustatask.model

class SearchMovie(
    val search: String,
    var page: Int,
    var searchResult: ArrayList<Movie> = arrayListOf()
)