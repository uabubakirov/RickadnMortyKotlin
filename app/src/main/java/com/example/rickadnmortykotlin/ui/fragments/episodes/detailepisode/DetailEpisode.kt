package com.example.rickadnmortykotlin.ui.fragments.episodes.detailepisode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.rickadnmortykotlin.common.base.BaseFragment
import com.example.rickadnmortykotlin.common.resource.Resource
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
            progressBar.isVisible = it is Resource.Loading
            group.isVisible = it !is Resource.Loading
            when(it){
                is Resource.Error -> {
                    Toast.makeText(requireContext(),it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    it.data?.let { data ->
                        txtName.text = data.name
                        txtAirDate.text = data.air_date
                        txtEpisode.text = data.episode
                    }
                }
            }
        })
    }

}