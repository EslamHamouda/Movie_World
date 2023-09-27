package com.android.movieapp.presentation.search.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.android.movieapp.R
import com.android.movieapp.databinding.FragmentHomeBinding
import com.android.movieapp.databinding.FragmentSearchBinding
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import com.android.movieapp.data.repo.SearchMoviesPagingSource
import com.android.movieapp.presentation.home.adapter.TrendingMoviesAdapter
import com.android.movieapp.presentation.home.ui.HomeFragmentDirections
import com.android.movieapp.presentation.home.viewmodel.HomeViewModel
import com.android.movieapp.presentation.search.adapter.SearchMoviesPagerAdapter
import com.android.movieapp.presentation.search.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()
    private val searchMoviesAdapter by lazy {
        SearchMoviesPagerAdapter(object : SearchMoviesPagerAdapter.OnItemClickListener {
            override fun onMovieClick(movieId: Int) {
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        handleOnBackPressed()
        binding.ivNavBack.setOnClickListener { popBackStackToHome() }
        binding.searchMovies.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.getSearchMovies(it)
                    collectSearchMovies()
                }
                return true
            }

        })
    }

    private fun setupUI() {
        binding.rvMoviesResult.adapter = searchMoviesAdapter
        searchMoviesAdapter.addLoadStateListener { loadState ->
            if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && searchMoviesAdapter.itemCount < 1) {
                binding.ivNotFound.visibility = View.VISIBLE
            } else binding.ivNotFound.visibility = View.GONE
        }
    }

    private fun collectSearchMovies() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getSearchMoviesResponse.collectLatest {
                    searchMoviesAdapter.submitData(it)
                }
            }
        }
    }

    private fun handleOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    popBackStackToHome()
                }
            })
    }

    private fun popBackStackToHome(){
        findNavController().popBackStack(R.id.homeFragment, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}