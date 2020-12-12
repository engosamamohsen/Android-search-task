package app.robusta.robustatask.module.main

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.robusta.robustatask.utils.Helper

class MainViewModel() : ViewModel() {

    val allowEdit: ObservableBoolean = ObservableBoolean(true)
    var query : String = ""
    val liveData: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    fun submit(){
        liveData.value = Helper.SUBMIT
    }
}