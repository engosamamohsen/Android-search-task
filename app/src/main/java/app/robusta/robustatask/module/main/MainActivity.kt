package app.robusta.robustatask.module.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import app.robusta.robustatask.R
import app.robusta.robustatask.databinding.ActivityMainBinding
import app.robusta.robustatask.module.movie.MovieAdapter
import app.robusta.robustatask.module.movie.MovieFragment
import app.robusta.robustatask.utils.Helper.Companion.SERVER_ERROR
import app.robusta.robustatask.module.movie.viewmodel.MovieViewModel
import app.robusta.robustatask.network.URLS.SEARCH_TIME_DELAY
import app.robusta.robustatask.utils.Helper
import app.robusta.robustatask.utils.MovementHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    var job: Job? = null

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = viewModel
        bindView()
        MovementHelper.replaceFragment(this,MovieFragment())
    }

    private fun bindView() {
        search.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_TIME_DELAY)
                editable?.let {
                    if (editable.trim().toString().isNotEmpty() || (viewModel.query != editable.trim().toString())) {
                        //send broadCastReciever
                        viewModel.query = editable.trim().toString()
                        val intent = Intent()
                        intent.action = Helper.SEARCH
                        intent.putExtra(Helper.SEARCH, viewModel.query)
                        sendBroadcast(intent)
                    }
                }
            }
        }
    }

}