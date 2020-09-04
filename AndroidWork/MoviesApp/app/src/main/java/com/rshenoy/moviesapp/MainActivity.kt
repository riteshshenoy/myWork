package com.rshenoy.moviesapp

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rshenoy.moviesapp.dataObject.Movie
import com.rshenoy.moviesapp.dataObject.PopularMoviesListOfResult
import com.rshenoy.moviesapp.utilities.Constant.API_KEY
import com.rshenoy.moviesapp.utilities.Constant.LANG
import com.rshenoy.moviesapp.utilities.GetMovieClient.client
import com.rshenoy.moviesapp.utilities.GetMovieDataInterface
import com.rshenoy.moviesapp.utilities.MovieRecyclerViewAdapter
import com.rshenoy.moviesapp.utilities.ScrollListenerForListOfPages
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

//Custom import
class MainActivity : AppCompatActivity() {
    private lateinit var movieRecyclerView: RecyclerView
    var movieRecyclerViewAdapter: MovieRecyclerViewAdapter? = null
    var movieList: ArrayList<Movie>? = null
    private var getMovieDataInterface: GetMovieDataInterface? = null
    private var currentPage = PAGE_START
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieRecyclerView = findViewById(R.id.recyclerview)
        movieList = ArrayList()
        val layoutManager = LinearLayoutManager(this)
        movieRecyclerView.setLayoutManager(layoutManager)
        movieRecyclerViewAdapter = MovieRecyclerViewAdapter(applicationContext, movieList)
        movieRecyclerView.setAdapter(movieRecyclerViewAdapter)
        getMovieDataInterface = client!!.create(GetMovieDataInterface::class.java)
        loadSinglePage()
        movieRecyclerView.addOnScrollListener(object : ScrollListenerForListOfPages(layoutManager) {
            override fun loadMoreItems() {
                currentPage += 1
                Handler().postDelayed({ loadSinglePage() }, 1000)
            }

            override val totalPageCount: Int
                get() = movieList!!.size
        })
    }

    private fun loadSinglePage() {
        Log.d(TAG, "loadNextPage: $currentPage")
        popularMoviesListOfResultCall()!!.enqueue(object : Callback<PopularMoviesListOfResult?> {
            override fun onResponse(call: Call<PopularMoviesListOfResult?>, response: Response<PopularMoviesListOfResult?>) {
                val popularMoviesListOfResult = response.body()
                Log.d("TAG", "Response Movie = $popularMoviesListOfResult")
                //movieRecyclerViewAdapter.setMovieList(popularMoviesListOfResult.getResults());
                movieRecyclerViewAdapter!!.addAllMovieList(popularMoviesListOfResult!!.results)
            }

            override fun onFailure(call: Call<PopularMoviesListOfResult?>, t: Throwable) {
                Log.d("TAG", "Response = $t")
            }
        })
    }

    private fun popularMoviesListOfResultCall(): Call<PopularMoviesListOfResult?>? {
        return getMovieDataInterface!!.getPopularMovies(
                API_KEY,
                LANG,
                currentPage
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    companion object {
        private const val PAGE_START = 1
        private const val TAG = "MainActivity"
    }
}