package app.robusta.robustatask.module.movie.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.robusta.robustatask.model.ListSearchHelper
import app.robusta.robustatask.model.MovieResponse
import app.robusta.robustatask.utils.Resource
import com.company.newskotlin.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    val liveData: MutableLiveData<Resource<MovieResponse>> by lazy {
        MutableLiveData<Resource<MovieResponse>>()
    }

    var search_position = -1
    var newList: Boolean = true
    var page = 0
    var movieResponse: MovieResponse? = null
    private var query: String = ""
    val haveData: ObservableBoolean = ObservableBoolean(true)
    val progress: ObservableBoolean = ObservableBoolean(false)
    val error: ObservableBoolean = ObservableBoolean(false)
    var currentPosition: ObservableField<String> = ObservableField("")
    init {
        getMovies()
    }

    private val TAG = "MovieViewModel"
    fun getMovies() = CoroutineScope(Dispatchers.IO).launch {
        try {
            backToDefault()
            progress.set(true)
            liveData.postValue(Resource.StopEdit())
            val response = if (query.trim()
                    .isEmpty()
            ) repository.getBreakingNews(page + 1) else repository.searchNews(
                query,
                page + 1
            )
            liveData.postValue(handleResponse(response))
        } catch (x: Throwable) {
            showError()
        }
    }

    var position_start = 0
    var new_items_count = 0

    private fun handleResponse(response: Response<MovieResponse>): Resource<MovieResponse> {
        if (response.isSuccessful) {
            progress.set(false)
            response.body()?.let { resultResponse ->
                page++
                if (this.movieResponse == null || page == 1 || newList) {
                    this.movieResponse = resultResponse
                    if(search_position != -1){
                        newList = true
                        val searchMovie = ListSearchHelper.searchResults[search_position]
                        searchMovie.page = page
                        searchMovie.searchResult.addAll(resultResponse.movieList)
                        movieResponse!!.movieList = ArrayList(searchMovie.searchResult)
                        ListSearchHelper.searchResults.set(search_position,searchMovie)

                        currentPosition.set("${movieResponse!!.movieList.size} / 1")
                    }
                } else {
                    val list = this.movieResponse?.movieList
                    list.let {
                        position_start = list!!.size + 1
                        val newItems = resultResponse.movieList
                        new_items_count = newItems.size
                        list.addAll(newItems)
                    }
                }
                newList = false
                return Resource.Success(this.movieResponse ?: resultResponse)
            }
        } else {
            showError()
        }
        return Resource.Error(response.message())
    }

    private fun backToDefault() {
        hideError()
        haveData.set(true)
    }

    private fun showError() {
        progress.set(false)
        error.set(true)
    }

    private fun hideError() {
        error.set(false)
    }

    fun top(){
        liveData.postValue(Resource.TOP())
    }

    fun retry() {
        getMovies()
    }

    override fun onCleared() {
        ListSearchHelper.searchResults.clear()
        super.onCleared()
    }

    fun setQuery(query: String) {
        newList = true
        movieResponse?.movieList?.clear()
        this.query = query
        if(query.trim().isEmpty()){
            search_position = -1
        }else {
            page = 0
            if (ListSearchHelper.checkExist(query)) {
                search_position = ListSearchHelper.getMovieIfExist(query)
                page = ListSearchHelper.searchResults[search_position].page
            }else{
                ListSearchHelper.addOrUpdate(query)
                search_position = ListSearchHelper.searchResults.size - 1
            }
        }
        getMovies()
    }

}