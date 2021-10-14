package vn.hailt.moviedemo.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import vn.hailt.moviedemo.R
import vn.hailt.moviedemo.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailViewModel =
            ViewModelProvider(this).get(DetailViewModel::class.java)
        val binding = DataBindingUtil.inflate<FragmentDetailBinding>(
            inflater,
            R.layout.fragment_detail,
            container,
            false
        )

        arguments?.let { bundle ->
            val movieId = bundle.getString("movie_id", "")

            if (movieId.isNotEmpty()) {
                detailViewModel.getMovieDetail(movieId).observe(viewLifecycleOwner, Observer {
                    it?.let { binding.detail = it }
                })

//                detailViewModel.getMovieVideos(movieId).observe(viewLifecycleOwner, Observer {
//                    Log.e("TAG", "onCreateView: $it")
//                })
            }
        }

        return binding.root
    }
}