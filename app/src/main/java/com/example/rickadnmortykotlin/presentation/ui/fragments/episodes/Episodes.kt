package com.example.rickadnmortykotlin.presentation.ui.fragments.episodes

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
import com.example.rickadnmortykotlin.common.extension.scrollListenNextPageEpisodes
import com.example.rickadnmortykotlin.databinding.FragmentEpisodesBinding
import com.example.rickadnmortykotlin.presentation.models.EpisodesUI
import com.example.rickadnmortykotlin.presentation.models.LocationUI
import com.example.rickadnmortykotlin.presentation.state.UIState
import com.example.rickadnmortykotlin.presentation.ui.adapters.EpisodesAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Episodes : BaseFragment<EpisodeViewModel, FragmentEpisodesBinding>(R.layout.fragment_episodes) {

    private val episodeAdapter = EpisodesAdapter(this::setupListeners)
    override val binding: FragmentEpisodesBinding by viewBinding()
    override val viewModel: EpisodeViewModel by viewModels()

    override fun initialize() = with(binding) {
        rvEpisode.layoutManager = LinearLayoutManager(requireContext())
        rvEpisode.adapter = episodeAdapter
    }

    override fun scrollListener() {
        binding.rvEpisode.scrollListenNextPageEpisodes(viewModel)
    }

    override fun setupRequests() {
        viewModel.dataEpisode.subscribe {
            when(it){
                is UIState.Error -> {}
                is UIState.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is UIState.Success -> {
                    val list = ArrayList<EpisodesUI>(episodeAdapter.currentList)
                    it.data.let { data -> list.addAll(data) }
                    episodeAdapter.submitList(list)
                    binding.progressBar.isVisible = false
                }
            }
        }
    }


    private fun setupListeners(id:Int,name: String) {
        findNavController().navigate(EpisodesDirections.actionEpisodesToDetailEpisode(name,id))
    }
}