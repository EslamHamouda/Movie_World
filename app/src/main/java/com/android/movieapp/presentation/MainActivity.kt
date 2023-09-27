package com.android.movieapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedDispatcher
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUiSaveStateControl
import androidx.navigation.ui.setupWithNavController
import com.android.movieapp.R
import com.android.movieapp.databinding.ActivityMainBinding
import com.android.movieapp.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> {
                    isShowBottomNavigation(false)
                }
                R.id.homeFragment -> {
                    isShowBottomNavigation(true)
                }
                R.id.detailsFragment -> {
                    isShowBottomNavigation(false)
                }
            }
        }
        disableOnItemReselectedReload()
    }

    private fun isShowBottomNavigation(visible: Boolean) {
        binding.bottomNavigationView.visibility = if (visible) View.VISIBLE else View.GONE
        binding.line.visibility = if (visible) View.VISIBLE else View.GONE
    }

    private fun disableOnItemReselectedReload(){
        binding.bottomNavigationView.setOnItemReselectedListener {}
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}