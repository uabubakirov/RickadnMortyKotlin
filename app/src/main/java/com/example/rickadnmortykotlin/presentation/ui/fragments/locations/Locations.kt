package com.example.rickadnmortykotlin.presentation.ui.fragments.locations

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickadnmortykotlin.R
import com.example.rickadnmortykotlin.common.base.BaseFragment
import com.example.rickadnmortykotlin.databinding.FragmentLocationsBinding
import com.example.rickadnmortykotlin.presentation.ui.adapters.LocationsAdapter
import com.example.rickadnmortykotlin.presentation.ui.adapters.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Locations : BaseFragment<LocationViewModel, FragmentLocationsBinding>(R.layout.fragment_locations) {

    private val locationAdapter = LocationsAdapter(this::setupListeners)
    override val binding: FragmentLocationsBinding by viewBinding()
    override val viewModel: LocationViewModel by viewModels()


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
        viewLifecycleOwner.lifecycleScope.launch {
        viewModel.fetchLocations().collectLatest {
            locationAdapter.submitData(it)
            }
        }
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