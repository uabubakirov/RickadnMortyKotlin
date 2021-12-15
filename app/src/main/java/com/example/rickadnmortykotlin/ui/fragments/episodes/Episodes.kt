package com.example.rickadnmortykotlin.ui.fragments.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickadnmortykotlin.base.fragment.BaseFragment
import com.example.rickadnmortykotlin.databinding.FragmentEpisodesBinding
import com.example.rickadnmortykotlin.ui.adapters.EpisodesAdapter
import com.example.rickadnmortykotlin.ui.adapters.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class Episodes : BaseFragment<EpisodeViewModel, FragmentEpisodesBinding>() {

    private val episodeAdapter = EpisodesAdapter(this::setupListeners)
    override lateinit var binding: FragmentEpisodesBinding
    override val viewModel: EpisodeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun initialize() = with(binding) {
        rvEpisode.layoutManager = LinearLayoutManager(requireContext())
        rvEpisode.adapter = episodeAdapter.withLoadStateFooter(LoadStateAdapter{
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

    override fun swipeRefresh()=with(binding) {
        swipeRefresh.setOnRefreshListener {
            Toast.makeText(requireContext(),"Обновлено", Toast.LENGTH_SHORT).show()
            swipeRefresh.isRefreshing = false
        }
    }

    fun setupListeners(id:Int,name: String) {
        findNavController().navigate(EpisodesDirections.actionEpisodesToDetailEpisode(name,id))


    }




}