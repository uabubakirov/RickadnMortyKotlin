package com.example.rickadnmortykotlin.ui.fragments.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickadnmortykotlin.R
import com.example.rickadnmortykotlin.base.fragment.BaseFragment
import com.example.rickadnmortykotlin.data.network.dtos.characters.CharactersModel
import com.example.rickadnmortykotlin.data.network.dtos.episodes.EpisodesModel
import com.example.rickadnmortykotlin.data.network.dtos.locations.LocationsModel
import com.example.rickadnmortykotlin.databinding.CharacterItemsBinding
import com.example.rickadnmortykotlin.databinding.FragmentLocationsBinding
import com.example.rickadnmortykotlin.ui.adapters.LocationsAdapter
import com.example.rickadnmortykotlin.ui.adapters.paging.LoadStateAdapter
import com.example.rickadnmortykotlin.utils.OnItemClick
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Locations : BaseFragment<LocationViewModel, FragmentLocationsBinding>() {

    private val locationAdapter = LocationsAdapter()
    private lateinit var binding1 :CharacterItemsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun initialize()= with(binding) {
        viewModel = ViewModelProvider(requireActivity()).get(LocationViewModel::class.java)
        rvLocation.layoutManager = LinearLayoutManager(requireContext())
        rvLocation.adapter = locationAdapter.withLoadStateFooter(LoadStateAdapter{
            locationAdapter.retry()
        })
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
            Toast.makeText(requireContext(),"Обновлено", Toast.LENGTH_SHORT).show()
            swipeRefresh.isRefreshing = false
        }
    }

    override fun setupListeners() {
        locationAdapter.onItemCLick(object :OnItemClick{
            override fun onItemClickLocation(location: LocationsModel, name: String) {
                viewModel.selectModel(location)
                Navigation.findNavController(requireView()).navigate(LocationsDirections.actionLocationsToDetailLocation(name))
            }

            override fun onItemCLick(character: CharactersModel, name: String) {
                TODO("Not yet implemented")
            }

            override fun onItemCLickEpisode(episode: EpisodesModel, name: String) {
                TODO("Not yet implemented")
            }
        })
    }

}