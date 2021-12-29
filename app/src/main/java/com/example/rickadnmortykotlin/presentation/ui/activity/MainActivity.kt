package com.example.rickadnmortykotlin.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.rickadnmortykotlin.R
import com.example.rickadnmortykotlin.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
    }

    private fun setupNavigation() {
        appBarConfiguration = AppBarConfiguration(setOf(R.id.characters,R.id.locations,R.id.episodes))
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        binding.btmNav.setupWithNavController(navController)
        binding.toolbar.setupWithNavController(navController,appBarConfiguration)
    }
}