package app.robusta.robustatask.module.movie

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.robusta.robustatask.R
import app.robusta.robustatask.databinding.FragmentMovieBinding
import app.robusta.robustatask.module.movie.viewmodel.MovieViewModel
import app.robusta.robustatask.utils.Helper
import app.robusta.robustatask.utils.Resource
import com.company.newskotlin.repository.MovieRepository


class MovieFragment : Fragment() {

    private val repository: MovieRepository by lazy {
        MovieRepository()
    }
    private val adapterMovie: MovieAdapter by lazy {
        MovieAdapter()
    }

    lateinit var binding: FragmentMovieBinding
    lateinit var viewModel: MovieViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        viewModel = MovieViewModel(repository)
        binding.viewmodel = viewModel
        bindRecycler()
        setEvent()
        return binding.root
    }

    var loading = true
    var pastVisiblesItems: Int = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0

    val layoutManagerMovie = null
    private fun bindRecycler() {
        val layoutManagerMovie = LinearLayoutManager(context)

        binding.rvSearchNews.apply {
            adapter = adapterMovie
            layoutManager = layoutManagerMovie
        }

        binding.rvSearchNews.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = layoutManagerMovie.childCount
                    totalItemCount = layoutManagerMovie.itemCount
                    viewModel.currentPosition.set("$totalItemCount / ${layoutManagerMovie.findFirstVisibleItemPosition()}")
                    pastVisiblesItems = layoutManagerMovie.findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            if(!viewModel.progress.get()) {
                                loading = false
                                viewModel.getMovies()
                                loading = true
                            }
                        }
                    }
                }else{
                    visibleItemCount = layoutManagerMovie.childCount
                    totalItemCount = layoutManagerMovie.itemCount
                    viewModel.currentPosition.set("$totalItemCount / " +
                            "${if (layoutManagerMovie.findFirstVisibleItemPosition() == 0 ) 1 else layoutManagerMovie.findFirstVisibleItemPosition()}")
                }
            }
        })
    }

    private val TAG = "MovieFragment"
    private fun setEvent() {
        viewModel.liveData.observe(this, Observer { response ->
            when (response) {

                is Resource.Success -> {
                    response.data?.let { newsResponse ->
                        binding.rvSearchNews.recycledViewPool.clear()
                        adapterMovie.differ.submitList(newsResponse.movieList.toList())
                        viewModel.haveData.set(!(newsResponse.movieList.size == 0 && viewModel.page == 1))
                        if(viewModel.page == 1){
                            viewModel.currentPosition.set("${adapterMovie.differ.currentList.size} / 1")
                        }
                        adapterMovie.notifyItemRangeInserted(
                            viewModel.position_start,
                            viewModel.new_items_count
                        );
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Toast.makeText(context, "An error occured: $message", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                is Resource.TOP -> {
                    binding.rvSearchNews.smoothScrollToPosition(0);
//                    layoutManagerMovie.scrollToPositionWithOffset(0, 0);
                }
            }
        })
    }


    private val search: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if(intent.hasExtra(Helper.SEARCH)){
                intent.getStringExtra(Helper.SEARCH)?.let { viewModel.setQuery(it) }
            }
        }
    }
    override fun onResume() {
        super.onResume()
        context!!.registerReceiver(search, IntentFilter(Helper.SEARCH))
    }

    override fun onPause() {
        super.onPause()
        context!!.unregisterReceiver(search)
    }
}