package vn.hailt.moviedemo.ui.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import vn.hailt.moviedemo.MainActivity
import vn.hailt.moviedemo.R
import vn.hailt.moviedemo.databinding.FragmentUpcomingBinding
import vn.hailt.moviedemo.model.MovieUpcoming

class UpcomingFragment : Fragment() {

    private lateinit var upcomingViewModel: UpcomingViewModel
    private val upcomingMovies = mutableListOf<MovieUpcoming>()
    private lateinit var upcomingAdapter: UpcomingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        upcomingViewModel = ViewModelProvider(this).get(UpcomingViewModel::class.java)
        val binding: FragmentUpcomingBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_upcoming, container, false)

        upcomingAdapter = UpcomingAdapter(upcomingMovies) { id ->
            val bundle = Bundle().apply {
                putString("movie_id", id)
            }
            (activity as MainActivity).navController.navigate(
                R.id.action_navigation_up_coming_to_navigation_detail,
                bundle
            )
        }
        binding.rvUpcoming.adapter = upcomingAdapter

        upcomingViewModel.getMovieUpcoming().observe(viewLifecycleOwner, Observer {
            it?.let {
                upcomingMovies.addAll(it.results)
                upcomingAdapter.notifyDataSetChanged()
            }
        })

        return binding.root
    }
}