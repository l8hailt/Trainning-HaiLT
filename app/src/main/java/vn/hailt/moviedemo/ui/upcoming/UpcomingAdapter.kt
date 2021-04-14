package vn.hailt.moviedemo.ui.upcoming

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vn.hailt.moviedemo.R
import vn.hailt.moviedemo.model.MovieUpcoming

class UpcomingAdapter(
    private val movies: List<MovieUpcoming>,
    private val listener: (id: String) -> Unit
) : RecyclerView.Adapter<UpcomingAdapter.UpcomingHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return UpcomingHolder(v)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: UpcomingHolder, position: Int) {
        holder.bind(movies[position])
    }

    inner class UpcomingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        private val tvOverview: TextView = itemView.findViewById(R.id.tv_overview)

        fun bind(movie: MovieUpcoming) {
            tvTitle.text = movie.title
            tvOverview.text = movie.overview

            itemView.setOnClickListener {
                listener.invoke(movie.id)
            }
        }
    }

}