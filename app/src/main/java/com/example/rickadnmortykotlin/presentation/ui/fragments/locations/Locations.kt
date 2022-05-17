package com.example.rickadnmortykotlin.presentation.ui.fragments.locations

import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickadnmortykotlin.R
import com.example.rickadnmortykotlin.common.base.BaseFragment
import com.example.rickadnmortykotlin.common.extension.scrollListenNextPageLocations
import com.example.rickadnmortykotlin.databinding.FragmentLocationsBinding
import com.example.rickadnmortykotlin.presentation.models.CharactersUI
import com.example.rickadnmortykotlin.presentation.models.FilterData
import com.example.rickadnmortykotlin.presentation.models.LocationUI
import com.example.rickadnmortykotlin.presentation.state.UIState
import com.example.rickadnmortykotlin.presentation.ui.adapters.LocationsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Locations : BaseFragment<LocationViewModel, FragmentLocationsBinding>(R.layout.fragment_locations) {

    private val locationAdapter = LocationsAdapter(this::setupClick)
    override val binding: FragmentLocationsBinding by viewBinding()
    override val viewModel: LocationViewModel by viewModels()
    private var filterData: FilterData? = FilterData()

    override fun initialize()= with(binding) {
        rvLocation.layoutManager = LinearLayoutManager(requireContext())
        rvLocation.adapter = locationAdapter
        filterData = arguments?.getParcelable("data")

    }

    override fun scrollListener() = with(binding) {
        rvLocation.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if(filterData == null){
                        viewModel.page++
                        viewModel.fetchLocations(viewModel.page)
                        Log.d("prost", "${viewModel.page}")
                    }


                }
            }
        })
    }

    override fun setupRequests() {
        if (filterData == null){
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
        }}else{
            viewModel.fetchLocationsByFilter(filterData?.param1,filterData?.param2,filterData?.param3)
            viewModel.stateLocationsFilter.subscribe {
                when(it){
                    is UIState.Error -> {}
                    is UIState.Loading ->{}
                    is UIState.Success -> {
                        locationAdapter.submitList(it.data)
                    }
                }
            }
        }
    }

    override fun setupListeners() {
        clickFilter()

    }

    private fun clickFilter() {
        binding.btnFilter.setOnClickListener {
            findNavController().navigate(R.id.locationFilter)
        }
    }

    private fun setupClick(id: Int,name: String) {
        findNavController().navigate(LocationsDirections.actionLocationsToDetailLocation(name,id))
    }
}