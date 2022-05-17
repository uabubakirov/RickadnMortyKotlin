package com.example.rickadnmortykotlin.presentation.ui.fragments.episodes

import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickadnmortykotlin.R
import com.example.rickadnmortykotlin.common.base.BaseFragment
import com.example.rickadnmortykotlin.common.extension.scrollListenNextPageEpisodes
import com.example.rickadnmortykotlin.databinding.FragmentEpisodesBinding
import com.example.rickadnmortykotlin.presentation.models.EpisodesUI
import com.example.rickadnmortykotlin.presentation.models.FilterData
import com.example.rickadnmortykotlin.presentation.models.LocationUI
import com.example.rickadnmortykotlin.presentation.state.UIState
import com.example.rickadnmortykotlin.presentation.ui.adapters.EpisodesAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Episodes : BaseFragment<EpisodeViewModel, FragmentEpisodesBinding>(R.layout.fragment_episodes) {

    private val episodeAdapter = EpisodesAdapter(this::setupCLick)



    override val binding: FragmentEpisodesBinding by viewBinding()
    override val viewModel: EpisodeViewModel by viewModels()
    private var filterData: FilterData? = FilterData()

    override fun initialize() = with(binding) {
        rvEpisode.layoutManager = LinearLayoutManager(requireContext())
        rvEpisode.adapter = episodeAdapter
        filterData = arguments?.getParcelable("data")
    }

    override fun scrollListener() = with(binding) {
        rvEpisode.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        if(filterData == null){
                            viewModel.page++
                            viewModel.fetchEpisodes(viewModel.page)
                            Log.d("prost", "${viewModel.page}")
                        }


                }
            }
        })
    }

    override fun setupRequests() {
        if(filterData == null){
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
        }}else{
            viewModel.fetchEpisodeByFilter(filterData?.param2,filterData?.param1)
            viewModel.dataEpisodeFilter.subscribe {
                when(it){
                    is UIState.Error -> {}
                    is UIState.Loading -> {}
                    is UIState.Success -> {

                        val list = ArrayList<EpisodesUI>(episodeAdapter.currentList)
                        it.data.let { data -> list.addAll(data) }
                        episodeAdapter.submitList(list)

                    }
                }
            }
        }
    }

    override fun setupListeners() {
        clickFilter()
    }

    private fun setupCLick(id:Int,name: String) {
        findNavController().navigate(EpisodesDirections.actionEpisodesToDetailEpisode(name,id))

    }

    private fun clickFilter() {
        binding.btnFilter.setOnClickListener {
            findNavController().navigate(R.id.filterEpisode)
        }
    }
}