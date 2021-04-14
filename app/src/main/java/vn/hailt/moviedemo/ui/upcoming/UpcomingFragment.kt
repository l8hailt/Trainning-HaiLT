package vn.hailt.moviedemo.ui.upcoming

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import vn.hailt.moviedemo.MainActivity
import vn.hailt.moviedemo.R
import vn.hailt.moviedemo.model.MovieUpcoming

class UpcomingFragment : Fragment() {

    private lateinit var upcomingViewModel: UpcomingViewModel
    private val upcomingMovies = mutableListOf<MovieUpcoming>()
    private lateinit var upcomingAdapter: UpcomingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        upcomingViewModel =
            ViewModelProvider(this).get(UpcomingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_upcoming, container, false)

        val rvUpcoming = root.findViewById<RecyclerView>(R.id.rv_upcoming)
        upcomingAdapter = UpcomingAdapter(upcomingMovies) { id ->
//            Toast.makeText(activity, id, Toast.LENGTH_SHORT).show()
            val bundle = Bundle().apply {
                putString("movie_id", id)
            }
            (activity as MainActivity).navController.navigate(
                R.id.action_navigation_up_coming_to_navigation_detail,
                bundle
            )
        }
        rvUpcoming.adapter = upcomingAdapter

        upcomingViewModel.getMovieUpcoming().observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.e("TAG", "onCreateView: " + it.results.size)
                upcomingMovies.addAll(it.results)
                upcomingAdapter.notifyDataSetChanged()
            }
        })

        return root
    }
}