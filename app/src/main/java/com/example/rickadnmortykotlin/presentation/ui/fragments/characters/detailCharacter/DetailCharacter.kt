package com.example.rickadnmortykotlin.presentation.ui.fragments.characters.detailCharacter

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.rickadnmortykotlin.R
import com.example.rickadnmortykotlin.common.base.BaseFragment
import com.example.rickadnmortykotlin.databinding.FragmentDetailCharacterBinding
import com.example.rickadnmortykotlin.presentation.state.UIState
import com.example.rickadnmortykotlin.presentation.ui.fragments.characters.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.viewModel

@AndroidEntryPoint
class DetailCharacter : BaseFragment<DetailCharacterViewModel,FragmentDetailCharacterBinding>(R.layout.fragment_detail_character){

    override val binding by viewBinding(FragmentDetailCharacterBinding :: bind)
    override val viewModel: DetailCharacterViewModel by viewModels()

    override fun initialize() {
        viewModel.fetchCharacter(DetailCharacterArgs.fromBundle(requireArguments()).id)
    }

    override fun setupRequests()= with(binding) {
        viewModel.stateCharacterDetail.subscribe {
            when (it) {
                is UIState.Error -> {
                    Toast.makeText(requireContext(),it.error,Toast.LENGTH_SHORT).show()
                }
                is UIState.Loading -> {
                    progressBar.isVisible = false
                    group.isVisible = true
                }
                is UIState.Success -> {

                    when (it.data.status) {
                        "Alive" -> imgIndicator.setImageResource(R.drawable.alive)
                        "Dead" -> imgIndicator.setImageResource(R.drawable.dead)
                        else -> imgIndicator.setImageResource(R.drawable.unknown)
                    }
                    txtName.text = it.data.name
                    txtGender.text = it.data.gender
                    txtSpecies.text = it.data.species
                    txtStatus.text = it.data.status
                    imgImage.load(it.data.image)

                }
            }
        }
    }
}

