package com.ethadien.yemeksiparisapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.ethadien.yemeksiparisapp.R
import com.ethadien.yemeksiparisapp.databinding.ActivityMainBinding
import com.ethadien.yemeksiparisapp.databinding.ActivitySplashBinding
import com.ethadien.yemeksiparisapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.bottomNavigationView.getOrCreateBadge(R.id.cartFragment).number =

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        NavigationUI.setupWithNavController(binding.bottomNavigationView, navHostFragment.navController)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}