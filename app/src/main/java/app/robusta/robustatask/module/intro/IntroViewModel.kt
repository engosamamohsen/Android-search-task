package app.robusta.robustatask.module.intro

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.robusta.robustatask.utils.Helper

class IntroViewModel() : ViewModel() {

    val liveData: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    fun submit(){
        liveData.value = Helper.SUBMIT
    }
}