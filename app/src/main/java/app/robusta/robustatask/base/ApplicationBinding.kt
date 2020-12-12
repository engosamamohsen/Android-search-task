package app.robusta.robustatask.base

import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import app.robusta.robustatask.network.URLS.POSTER_BASE_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

private val TAG: String? = "ApplicationBinding"

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView?, image: Any?) {

    if (image is String) {
        imageView?.let {
            Glide.with(it.context)
                .load(POSTER_BASE_URL+image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(it)
        }
    } else if (image is Int) {
        imageView?.setImageResource(image)
    }
}
