package com.rshenoy.moviesapp.utilities

import com.rshenoy.moviesapp.dataObject.PopularMoviesListOfResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetMovieDataInterface {
    @GET("movie/popular")
    fun getPopularMovies(
            @Query("api_key") apiKey: String?,
            @Query("language") language: String?,
            @Query("page") pageIndex: Int
    ): Call<PopularMoviesListOfResult?>?
}