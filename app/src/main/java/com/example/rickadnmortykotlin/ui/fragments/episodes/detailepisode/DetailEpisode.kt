package com.example.rickadnmortykotlin.ui.fragments.episodes.detailepisode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.rickadnmortykotlin.base.fragment.BaseFragment
import com.example.rickadnmortykotlin.databinding.FragmentDetailEpisodeBinding
import com.example.rickadnmortykotlin.ui.fragments.episodes.EpisodeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailEpisode : BaseFragment<EpisodeViewModel, FragmentDetailEpisodeBinding>() {

    override lateinit var binding: FragmentDetailEpisodeBinding
    override val viewModel: EpisodeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailEpisodeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun setupObservers()= with(binding) {
        viewModel.fetchEpisode(DetailEpisodeArgs.fromBundle(requireArguments()).id).observe(viewLifecycleOwner,{
            txtName.text = it.name
            txtEpisode.text = it.episode
            txtAirDate.text = it.air_date
        })
    }

}