package app.robusta.robustatask.utils

import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import app.robusta.robustatask.base.MyApplication

object ResourceManager {
    fun getString(id: Int): String {
        return MyApplication.instance.resources.getString(id)
    }

    fun getColor(id: Int): Int? {
        return MyApplication.instance.resources?.getColor(id)
    }

    fun getDrawable(id: Int): Drawable? {
        return MyApplication.instance.let { ContextCompat.getDrawable(it, id) }
    }

    fun getDimens(id: Int): Float? {
        return MyApplication.instance.resources?.getDimension(id)
    }
}