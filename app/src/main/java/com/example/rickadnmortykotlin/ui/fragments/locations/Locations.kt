package com.example.rickadnmortykotlin.ui.fragments.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickadnmortykotlin.common.base.BaseFragment
import com.example.rickadnmortykotlin.databinding.FragmentLocationsBinding
import com.example.rickadnmortykotlin.ui.adapters.LocationsAdapter
import com.example.rickadnmortykotlin.ui.adapters.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Locations : BaseFragment<LocationViewModel, FragmentLocationsBinding>() {

    private val locationAdapter = LocationsAdapter(this::setupListeners)
    override lateinit var binding: FragmentLocationsBinding
    override val viewModel: LocationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun initialize()= with(binding) {
        rvLocation.layoutManager = LinearLayoutManager(requireContext())
        rvLocation.adapter = locationAdapter.withLoadStateFooter(LoadStateAdapter{
            locationAdapter.retry()
        })
        locationAdapter.addLoadStateListener { loadStates ->
            rvLocation.isVisible = loadStates.refresh is LoadState.NotLoading
            progressBar.isVisible = loadStates.refresh is LoadState.Loading
        }
    }

    override fun setupRequests() {
        viewModel.fetchLocations().observe(viewLifecycleOwner, {
            viewLifecycleOwner.lifecycleScope.launch {
                locationAdapter.submitData(it)
            }
        })
    }

    override fun swipeRefresh()= with(binding) {
        swipeRefresh.setOnRefreshListener {
            locationAdapter.refresh()
            Toast.makeText(requireContext(), "Обновлено", Toast.LENGTH_SHORT).show()
            swipeRefresh.isRefreshing = false
        }
    }

    fun setupListeners(id: Int,name: String) {
        findNavController().navigate(LocationsDirections.actionLocationsToDetailLocation(name,id))
    }




}