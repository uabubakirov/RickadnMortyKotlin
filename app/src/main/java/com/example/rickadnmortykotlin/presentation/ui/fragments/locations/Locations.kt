package com.example.rickadnmortykotlin.presentation.ui.fragments.locations

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickadnmortykotlin.R
import com.example.rickadnmortykotlin.common.base.BaseFragment
import com.example.rickadnmortykotlin.common.extension.scrollListenNextPageLocations
import com.example.rickadnmortykotlin.databinding.FragmentLocationsBinding
import com.example.rickadnmortykotlin.presentation.models.CharactersUI
import com.example.rickadnmortykotlin.presentation.models.LocationUI
import com.example.rickadnmortykotlin.presentation.state.UIState
import com.example.rickadnmortykotlin.presentation.ui.adapters.LocationsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Locations : BaseFragment<LocationViewModel, FragmentLocationsBinding>(R.layout.fragment_locations) {

    private val locationAdapter = LocationsAdapter(this::setupListeners)
    override val binding: FragmentLocationsBinding by viewBinding()
    override val viewModel: LocationViewModel by viewModels()

    override fun initialize()= with(binding) {
        rvLocation.layoutManager = LinearLayoutManager(requireContext())
        rvLocation.adapter = locationAdapter

    }

    override fun scrollListener() {
        binding.rvLocation.scrollListenNextPageLocations(viewModel)
    }

    override fun setupRequests() {
        viewModel.stateLocations.subscribe {
            when(it){
                is UIState.Error -> {}
                is UIState.Loading -> {
                    if (viewModel.count == 0){
                        binding.progressBar.isVisible = true
                        viewModel.count++
                    }else{
                        binding.progressBarPage.isVisible = true
                    }
                }
                is UIState.Success -> {
                    val list = ArrayList<LocationUI>(locationAdapter.currentList)
                    it.data.let { data -> list.addAll(data) }
                    locationAdapter.submitList(list)
                    binding.progressBar.isVisible = false
                    binding.progressBarPage.isVisible = false
                }
            }
        }
    }

    private fun setupListeners(id: Int,name: String) {
        findNavController().navigate(LocationsDirections.actionLocationsToDetailLocation(name,id))
    }
}