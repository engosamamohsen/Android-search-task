package app.robusta.robustatask.network

import app.robusta.robustatask.model.Movie
import app.robusta.robustatask.model.MovieResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import app.robusta.robustatask.network.URLS.BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface NetworkApi {

    @GET("movie/popular")
    suspend fun getBreakingNews(
        @Query("page")
        pageNumber : Int = 1 ,
        @Query("api_key")
        apiKey : String = URLS.API_KEY
    ) : Response<MovieResponse>


    @GET("search/movie")
    suspend fun searchForNews(
        @Query("query")
        querySearch: String,
        @Query("page")
        pageNumber : Int = 1,
        @Query("api_key")
        apiKey : String = URLS.API_KEY,
        @Query("language")
        language : String = "en-US",
        @Query("include_adult")
        includeAdult : Boolean = false,
    ) : Response<MovieResponse>

}