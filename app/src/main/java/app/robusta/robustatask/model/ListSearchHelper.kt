package app.robusta.robustatask.model

import java.util.*
import kotlin.collections.ArrayList

class ListSearchHelper {


    companion object{
        var searchResults: ArrayList<SearchMovie> = arrayListOf()

        fun checkExist(search: String) : Boolean{
            for(searchResult in searchResults){
                if(search == searchResult.search) return true
            }
            return false
        }

        fun addOrUpdate(search: String, newMovies : ArrayList<Movie> = arrayListOf()): SearchMovie{
//            if(!checkExist(search)){
//                // add new item
//                searchResults.add(SearchMovie(search,0,newMovies))
//                return searchResults.last()
//            }else{
//                val pos = getMovieIfExist(search)
//                return searchResults[pos]
//            }
            searchResults.add(SearchMovie(search,0,newMovies))
            return searchResults.last()
        }

        fun getMovieIfExist(search: String) : Int{
            for(i in 0 until searchResults.size){
                if(search == searchResults[i].search) return i
            }
            return -1
        }
    }
}