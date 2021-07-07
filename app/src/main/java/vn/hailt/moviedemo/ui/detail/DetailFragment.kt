package vn.hailt.moviedemo.ui.detail

import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import vn.hailt.moviedemo.R

class DetailFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailViewModel =
            ViewModelProvider(this).get(DetailViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_detail, container, false)

        val imgBackdrop: ImageView = root.findViewById(R.id.img_backdrop)
        val tvTitle: TextView = root.findViewById(R.id.tv_title)
        val tvOverview: TextView = root.findViewById(R.id.tv_overview)
        val tvVoteAverage: TextView = root.findViewById(R.id.tv_vote_average)
        val tvVoteCount: TextView = root.findViewById(R.id.tv_vote_count)

        arguments?.let { bundle ->
            val movieId = bundle.getString("movie_id", "")

            if (movieId.isNotEmpty()) {
                detailViewModel.getMovieDetail(movieId).observe(viewLifecycleOwner, Observer {
                    it?.apply {
                        Log.e("TAG", "onCreateView: $it")
                        Glide.with(this@DetailFragment)
                            .load("https://image.tmdb.org/t/p/original$backdropPath")
                            .into(imgBackdrop)
                        tvTitle.text = title
                        tvOverview.text = overview
                        tvVoteAverage.text = voteAverage.toString()
                        tvVoteCount.text = voteCount.toString()
                    }
                })

                detailViewModel.getMovieVideos(movieId).observe(viewLifecycleOwner, Observer {
                    Log.e("TAG", "onCreateView: $it")
                })
            }
        }

        return root
    }
}