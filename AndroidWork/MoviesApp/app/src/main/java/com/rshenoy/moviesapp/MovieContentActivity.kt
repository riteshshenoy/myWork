package com.rshenoy.moviesapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rshenoy.moviesapp.dataObject.Movie
import com.rshenoy.moviesapp.utilities.Constant.BASE_IMAGE_URL
import com.rshenoy.moviesapp.utilities.Constant.EXTRA_MOVIE

class MovieContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        val blurImage = findViewById<ImageView>(R.id.background_image)
        val poster = findViewById<ImageView>(R.id.movie_image)
        val title = findViewById<TextView>(R.id.movie_title)
        val release = findViewById<TextView>(R.id.movie_release_label)
        val originalTitle = findViewById<TextView>(R.id.movie_original_title)
        val originalLanguage = findViewById<TextView>(R.id.movie_original_language_value)
        val voteAverage = findViewById<TextView>(R.id.vote_average_value)
        val popularity = findViewById<TextView>(R.id.popularity_value)
        val overview = findViewById<TextView>(R.id.movie_overview)
        val movieItem = intent.getSerializableExtra(EXTRA_MOVIE) as Movie
        title.text = movieItem.title
        release.text = movieItem.releaseDate
        originalTitle.text = movieItem.originalTitle
        originalLanguage.text = movieItem.originalLanguage
        voteAverage.text = movieItem.voteAverage.toString()
        popularity.text = movieItem.popularity
        overview.text = movieItem.overview
        val postPath = BASE_IMAGE_URL + movieItem.posterPath
        Glide.with(this).load(postPath).apply(RequestOptions.centerCropTransform()).into(blurImage)
        Glide.with(this).load(postPath).into(poster)
    }
}