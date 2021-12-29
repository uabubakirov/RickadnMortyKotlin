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
import com.example.rickadnmortykotlin.databinding.FragmentEpisodesBinding
import com.example.rickadnmortykotlin.presentation.ui.adapters.EpisodesAdapter
import com.example.rickadnmortykotlin.presentation.ui.adapters.paging.LoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class Episodes : BaseFragment<EpisodeViewModel, FragmentEpisodesBinding>(R.layout.fragment_episodes) {

    private val episodeAdapter = EpisodesAdapter(this::setupListeners)
    override val binding: FragmentEpisodesBinding by viewBinding()
    override val viewModel: EpisodeViewModel by viewModel()

    override fun initialize() = with(binding) {
        rvEpisode.layoutManager = LinearLayoutManager(requireContext())
        rvEpisode.adapter = episodeAdapter.withLoadStateFooter(LoadStateAdapter{
            episodeAdapter.retry()
        })
        episodeAdapter.addLoadStateListener { loadStates ->
            rvEpisode.isVisible = loadStates.refresh is LoadState.NotLoading
            progressBar.isVisible = loadStates.refresh is LoadState.Loading
        }
    }

    override fun setupRequests() {
        viewLifecycleOwner.lifecycleScope.launch {
        viewModel.fetchEpisodes().collectLatest {
            episodeAdapter.submitData(it)
            }
        }
    }

    override fun swipeRefresh()=with(binding) {
        swipeRefresh.setOnRefreshListener {
            episodeAdapter.refresh()
            Toast.makeText(requireContext(), "Обновлено", Toast.LENGTH_SHORT).show()
            swipeRefresh.isRefreshing = false
        }
    }

    private fun setupListeners(id:Int,name: String) {
        findNavController().navigate(EpisodesDirections.actionEpisodesToDetailEpisode(name,id))
    }
}