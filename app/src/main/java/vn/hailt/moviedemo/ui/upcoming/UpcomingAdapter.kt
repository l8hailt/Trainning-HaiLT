package vn.hailt.moviedemo.ui.upcoming

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import vn.hailt.moviedemo.R
import vn.hailt.moviedemo.databinding.ItemMovieBinding
import vn.hailt.moviedemo.model.MovieUpcoming

class UpcomingAdapter(
    private val movies: List<MovieUpcoming>,
    private val listener: (id: String) -> Unit
) : RecyclerView.Adapter<UpcomingAdapter.UpcomingHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemMovieBinding>(inflater, R.layout.item_movie, parent, false)
        return UpcomingHolder(binding)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: UpcomingHolder, position: Int) {
        holder.bind(movies[position])
    }

    inner class UpcomingHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieUpcoming) {
            binding.root.setOnClickListener {
                listener.invoke(movie.id)
            }
            binding.movie = movie
        }
    }

}