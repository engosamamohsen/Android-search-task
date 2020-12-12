package app.robusta.robustatask.module.movie


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.robusta.robustatask.R
import app.robusta.robustatask.databinding.ItemMovieBinding
import app.robusta.robustatask.model.Movie
import app.robusta.robustatask.module.movie.viewmodel.ItemMovieViewModel

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    val TAG = "NewsTAGAdapter"
    private val differCallback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        // binding item_model layout
        val binding: ItemMovieBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie,
                parent,
                false
        )
        return MovieViewHolder(
                binding
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val Movie = differ.currentList[position]
        holder.bind(differ.currentList[position])
    }

    private var onItemClickListener: ((Movie) -> Unit)? = null

    fun setOnItemClickListener(listener: (Movie) -> Unit) {
        onItemClickListener = listener
    }

    class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root){
        lateinit var viewModel: ItemMovieViewModel

        //bint
        fun bind(model: Movie) {
            viewModel = ItemMovieViewModel(model)
            binding.viewmodel = viewModel
        }
    }

}

