package app.robusta.robustatask.network

//https://api.themoviedb.org/3/search/movie?api_key=6e63c2317fbe963d76c3bdc2b785f6d1&language=en-US&query=ts&page=1&include_adult=false
object URLS {
    const val API_KEY = "6e63c2317fbe963d76c3bdc2b785f6d1"
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"
    const val SEARCH_TIME_DELAY = 500L
    const val FIRST_PAGE = 1
    const val POST_PER_PAGE = 10

}