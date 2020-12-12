package com.company.newskotlin.repository

import app.robusta.robustatask.network.RetrofitInstance


class MovieRepository() {
    suspend fun getBreakingNews(pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(pageNumber)

    suspend fun searchNews(query: String , pageNumber: Int) =
        RetrofitInstance.api.searchForNews(query,pageNumber)

}