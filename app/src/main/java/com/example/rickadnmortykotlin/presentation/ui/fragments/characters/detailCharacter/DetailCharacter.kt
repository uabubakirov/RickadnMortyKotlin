package com.example.rickadnmortykotlin.presentation.ui.fragments.characters.detailCharacter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import coil.load
import com.example.rickadnmortykotlin.R
import com.example.rickadnmortykotlin.common.base.BaseFragment
import com.example.rickadnmortykotlin.databinding.FragmentDetailCharacterBinding
import com.example.rickadnmortykotlin.presentation.state.UIState
import com.example.rickadnmortykotlin.presentation.ui.fragments.characters.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCharacter : BaseFragment<CharactersViewModel,FragmentDetailCharacterBinding>(R.layout.fragment_detail_character){

    override lateinit var binding: FragmentDetailCharacterBinding
    override val viewModel: CharactersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailCharacterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchCharacter(DetailCharacterArgs.fromBundle(requireArguments()).id)
        initialize1()
    }

    private fun initialize1()= with(binding) {
        viewModel.data.subscribe {
            when (it) {
                is UIState.Error -> {}
                is UIState.Loading -> {}
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

