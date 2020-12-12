package app.robusta.robustatask.module.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import app.robusta.robustatask.R
import app.robusta.robustatask.databinding.ActivityIntroBinding
import app.robusta.robustatask.module.main.MainActivity

class IntroActivity : AppCompatActivity() {

    private val viewModel: IntroViewModel by lazy {
        ViewModelProviders.of(this).get(IntroViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val binding: ActivityIntroBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_intro)
        binding.viewModel = viewModel
        setEvent()
    }
    private fun setEvent() {
        viewModel.liveData.observe(this, Observer {
            finishAffinity()
            startActivity(Intent(this, MainActivity::class.java))
        })
    }
}