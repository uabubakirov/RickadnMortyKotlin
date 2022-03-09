package com.example.rickadnmortykotlin.presentation.ui.fragments.episodes.detailepisode

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels

import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickadnmortykotlin.R
import com.example.rickadnmortykotlin.common.base.BaseFragment
import com.example.rickadnmortykotlin.databinding.FragmentDetailEpisodeBinding
import com.example.rickadnmortykotlin.presentation.state.UIState
import com.example.rickadnmortykotlin.presentation.ui.fragments.episodes.EpisodeViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.viewModel

@AndroidEntryPoint
class DetailEpisode : BaseFragment<DetailEpisodeViewModel, FragmentDetailEpisodeBinding>(R.layout.fragment_detail_episode) {

    override val binding by viewBinding(FragmentDetailEpisodeBinding :: bind)
    override val viewModel: DetailEpisodeViewModel by viewModels()

    override fun initialize() {
        viewModel.fetchEpisode(DetailEpisodeArgs.fromBundle(requireArguments()).id)
    }

    override fun setupObservers() = with(binding) {
        viewModel.stateEpisodeDetail.subscribe{
            when(it){
                is UIState.Error -> {
                    Toast.makeText(requireContext(),it.error,Toast.LENGTH_SHORT).show()
                }
                is UIState.Loading -> {
                    progressBar.isVisible = false
                    group.isVisible = true
                }
                is UIState.Success -> {
                    txtName.text = it.data.name
                    txtAirDate.text = it.data.air_date
                    txtEpisode.text = it.data.episode
                }
            }
        }
    }
}