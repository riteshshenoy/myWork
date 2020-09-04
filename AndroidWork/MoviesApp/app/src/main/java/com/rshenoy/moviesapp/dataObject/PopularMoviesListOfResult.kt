package com.rshenoy.moviesapp.dataObject

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class PopularMoviesListOfResult : Serializable {
    @SerializedName("results")
    @Expose
    var results: List<Movie> = ArrayList()

}