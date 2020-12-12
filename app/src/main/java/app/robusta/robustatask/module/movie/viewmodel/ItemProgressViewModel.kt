package app.robusta.robustatask.module.movie.viewmodel

import androidx.databinding.ObservableBoolean

class ItemProgressViewModel(){
    val progress = ObservableBoolean(false)
    val error = ObservableBoolean(false)
}