package com.example.rickadnmortykotlin.ui.fragments.episodes

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
import com.example.rickadnmortykotlin.databinding.FragmentEpisodesBinding
import com.example.rickadnmortykotlin.ui.adapters.EpisodesAdapter
import com.example.rickadnmortykotlin.ui.adapters.paging.LoadStateAdapter
import com.example.rickadnmortykotlin.utils.OnItemClick
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class Episodes : BaseFragment<EpisodeViewModel, FragmentEpisodesBinding>() {

    private val episodeAdapter = EpisodesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun initialize() {
        viewModel = ViewModelProvider(requireActivity()).get(EpisodeViewModel::class.java)
        binding.rvEpisode.layoutManager = LinearLayoutManager(requireContext())
        binding.rvEpisode.adapter = episodeAdapter.withLoadStateFooter(LoadStateAdapter{
            episodeAdapter.retry()
        })
    }

    override fun setupRequests() {
        viewModel.fetchEpisodes().observe(viewLifecycleOwner, {
            viewLifecycleOwner.lifecycleScope.launch {
                episodeAdapter.submitData(it)
            }
        })
    }

    override fun swipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            Toast.makeText(requireContext(),"Обновлено", Toast.LENGTH_SHORT).show()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    override fun setupListeners() {
        episodeAdapter.onItemClick(object:OnItemClick {
            override fun onItemCLickEpisode(episode: EpisodesModel, name: String) {
                viewModel.selectModel(episode)
                Navigation.findNavController(requireView()).navigate(EpisodesDirections.actionEpisodesToDetailEpisode(name))
            }

            override fun onItemCLick(character: CharactersModel, name: String) {
                TODO("Not yet implemented")
            }
            override fun onItemClickLocation(location: LocationsModel, name: String) {
                TODO("Not yet implemented")
            }
        })
    }

}