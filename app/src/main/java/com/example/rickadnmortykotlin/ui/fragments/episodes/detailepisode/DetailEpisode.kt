package com.example.rickadnmortykotlin.ui.fragments.episodes.detailepisode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.rickadnmortykotlin.base.fragment.BaseFragment
import com.example.rickadnmortykotlin.databinding.FragmentDetailEpisodeBinding
import com.example.rickadnmortykotlin.ui.fragments.episodes.EpisodeViewModel


class DetailEpisode : BaseFragment<EpisodeViewModel, FragmentDetailEpisodeBinding>() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailEpisodeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun initialize() {
        viewModel = ViewModelProvider(requireActivity()).get(EpisodeViewModel::class.java)
    }

    override fun setupObservers() {
        viewModel.getModel().observe(viewLifecycleOwner,{
            binding.txtName.text = it.name
            binding.txtAirDate.text = it.air_date
            binding.txtEpisode.text = it.episode
        })
    }
}