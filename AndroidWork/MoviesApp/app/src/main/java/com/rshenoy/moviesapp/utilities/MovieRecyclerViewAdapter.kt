package com.rshenoy.moviesapp.utilities

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rshenoy.moviesapp.MovieContentActivity
import com.rshenoy.moviesapp.R
import com.rshenoy.moviesapp.dataObject.Movie
import com.rshenoy.moviesapp.utilities.Constant.BASE_IMAGE_URL
import com.rshenoy.moviesapp.utilities.Constant.EXTRA_MOVIE
import com.rshenoy.moviesapp.utilities.MovieRecyclerViewAdapter.CustomViewHolder
import java.util.*

//Custom import
class MovieRecyclerViewAdapter(var context: Context, var movieList: ArrayList<Movie>?) : RecyclerView.Adapter<CustomViewHolder>() {

    /*public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }*/
    fun addMovie(movie: Movie) {
        movieList!!.add(movie)
        notifyItemInserted(movieList!!.size - 1)
    }

    fun addAllMovieList(tempMovieList: List<Movie>) {
        for (movie in tempMovieList) {
            addMovie(movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview_adapter_layout, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val movieListPosition = movieList!![position]
        holder.movieTitle.text = movieListPosition.title
        holder.movieOverview.text = movieListPosition.overview
        val finalYearLang = movieListPosition.releaseDate!!.substring(0, 4) +
                " | " + movieListPosition.originalLanguage!!.toUpperCase()
        holder.movieYear.text = finalYearLang
        Glide.with(context).load(BASE_IMAGE_URL + movieListPosition.posterPath).apply(RequestOptions.centerCropTransform()).into(holder.movieImage)
        holder.itemView.setOnClickListener { view ->
            val context = view.context
            val intent = Intent(context, MovieContentActivity::class.java)
            intent.putExtra(EXTRA_MOVIE, movieListPosition)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return if (movieList != null) {
            movieList!!.size
        } else 0
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var movieTitle: TextView
        var movieOverview: TextView
        var movieYear: TextView
        var movieImage: ImageView

        init {
            movieTitle = itemView.findViewById(R.id.movie_title)
            movieYear = itemView.findViewById(R.id.movie_year)
            movieOverview = itemView.findViewById(R.id.movie_overview)
            movieImage = itemView.findViewById(R.id.movie_image)
        }
    }

}