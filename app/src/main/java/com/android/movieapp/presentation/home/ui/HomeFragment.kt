package com.android.movieapp.presentation.home.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import com.android.movieapp.R
import com.android.movieapp.databinding.FragmentHomeBinding
import com.android.movieapp.domain.model.MovieListCategoryDomainModel
import com.android.movieapp.presentation.home.adapter.MovieListCategoryAdapter
import com.android.movieapp.presentation.home.adapter.NowPlayingMoviesPagerAdapter
import com.android.movieapp.presentation.home.adapter.PopularMoviesPagerAdapter
import com.android.movieapp.presentation.home.adapter.TopRatedMoviesPagerAdapter
import com.android.movieapp.presentation.home.adapter.TrendingMoviesAdapter
import com.android.movieapp.presentation.home.adapter.UpcomingMoviesPagerAdapter
import com.android.movieapp.presentation.home.viewmodel.HomeViewModel
import com.android.movieapp.presentation.home.viewmodel.TrendingMoviesHomeStates
import com.android.movieapp.utils.isShowProgressBar
import com.android.movieapp.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private val trendingMoviesAdapter by lazy {
        TrendingMoviesAdapter(object : TrendingMoviesAdapter.OnItemClickListener {
            override fun onTrendingMovieClick(movieId: Int) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movieId))
            }
        })
    }
    private val movieListCategoryAdapter by lazy {
        MovieListCategoryAdapter(object : MovieListCategoryAdapter.OnItemClickListener {
            override fun onCategoryClicked(categoryId: Int) {
                when (categoryId) {
                    1 -> {
                        collectNowPlayingMovies()
                    }

                    2 -> {
                        collectUpcomingMovies()
                    }

                    3 -> {
                        collectTopRatedMovies()
                    }

                    4 -> {
                        collectPopularMovies()
                    }
                }
            }
        })
    }
    private val nowPlayingMoviesPagerAdapter by lazy {
        NowPlayingMoviesPagerAdapter(object : NowPlayingMoviesPagerAdapter.OnItemClickListener {
            override fun onMovieClick(movieId: Int) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movieId))
            }
        })
    }
    private val upcomingMoviesPagerAdapter by lazy {
        UpcomingMoviesPagerAdapter(object : UpcomingMoviesPagerAdapter.OnItemClickListener {
            override fun onMovieClick(movieId: Int) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movieId))
            }
        })
    }
    private val topRatedMoviesPagerAdapter by lazy {
        TopRatedMoviesPagerAdapter(object : TopRatedMoviesPagerAdapter.OnItemClickListener {
            override fun onMovieClick(movieId: Int) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movieId))
            }
        })
    }
    private val popularMoviesPagerAdapter by lazy {
        PopularMoviesPagerAdapter(object : PopularMoviesPagerAdapter.OnItemClickListener {
            override fun onMovieClick(movieId: Int) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movieId))
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater)
        viewModel.getTrendingMovies()
        viewModel.getNowPlayingMovies()
        viewModel.getUpcomingMovies()
        viewModel.getTopRatedMovies()
        viewModel.getPopularMovies()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        handleOnBackPressed()
        collectTrendingMovies()
        collectNowPlayingMovies()
    }

    private fun setupUI() {
        binding.rvTrendingMovies.adapter = trendingMoviesAdapter
        binding.rvMoviesList.adapter = movieListCategoryAdapter
        movieListCategoryAdapter.submitList(
            listOf(
                MovieListCategoryDomainModel(1, "Now Playing"),
                MovieListCategoryDomainModel(2, "Upcoming"),
                MovieListCategoryDomainModel(3, "Top Rated"),
                MovieListCategoryDomainModel(4, "Popular")
            )
        )
    }

    private fun collectTrendingMovies() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getVendorCategoryResponse.collectLatest {
                    when (it) {
                        is TrendingMoviesHomeStates.Loading -> {
                            binding.progressBar.progressBar.isShowProgressBar(it.isLoading)
                        }

                        is TrendingMoviesHomeStates.Success -> {
                            trendingMoviesAdapter.submitList(it.value)
                        }

                        is TrendingMoviesHomeStates.Failure -> {
                            it.throwable.message?.let { it1 ->
                                showSnackBar(
                                    it1,
                                    requireActivity()
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun collectNowPlayingMovies() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getNowPlayingMoviesResponse.collectLatest {
                    nowPlayingMoviesPagerAdapter.submitData(it)
                }
            }
        }
        binding.rvMoviesListContent.adapter = nowPlayingMoviesPagerAdapter
    }
    private fun collectUpcomingMovies() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getUpcomingMoviesResponse.collectLatest {
                    upcomingMoviesPagerAdapter.submitData(it)
                }
            }
        }
        binding.rvMoviesListContent.adapter = upcomingMoviesPagerAdapter
    }
    private fun collectTopRatedMovies() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getTopRatedMoviesResponse.collectLatest {
                    topRatedMoviesPagerAdapter.submitData(it)
                }
            }
        }
        binding.rvMoviesListContent.adapter = topRatedMoviesPagerAdapter
    }

    private fun collectPopularMovies() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getPopularMoviesResponse.collectLatest {
                    popularMoviesPagerAdapter.submitData(it)
                }
            }
        }
        binding.rvMoviesListContent.adapter = popularMoviesPagerAdapter
    }
    private fun handleOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            })
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}