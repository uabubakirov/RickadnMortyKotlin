package com.example.rickadnmortykotlin.presentation.ui.fragments.characters

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
import com.example.rickadnmortykotlin.common.extension.scrollListenNextPageCharacters
import com.example.rickadnmortykotlin.databinding.FragmentCharactersBinding
import com.example.rickadnmortykotlin.presentation.models.CharactersUI
import com.example.rickadnmortykotlin.presentation.state.UIState
import com.example.rickadnmortykotlin.presentation.ui.adapters.CharactersAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@AndroidEntryPoint
class Characters : BaseFragment<CharactersViewModel, FragmentCharactersBinding>(R.layout.fragment_characters) {

    private val charactersAdapter =
        CharactersAdapter(this::setupListeners, this::setupLongListeners)
    override val binding by viewBinding(FragmentCharactersBinding::bind)
    override val viewModel: CharactersViewModel by viewModels()

    override fun initialize() = with(binding) {
        rvCharacter.layoutManager = LinearLayoutManager(requireContext())
        rvCharacter.adapter = charactersAdapter

    }

    override fun setupRequests() {
        viewModel.stateCharacters.subscribe {
            when(it){
                is UIState.Error -> {}
                is UIState.Loading -> {
                    if (viewModel.count == 0){
                        binding.progressBar.isVisible = true
                        viewModel.count++
                    }else{
                        binding.progressBarPage.isVisible = true
                        }
                    }

                is UIState.Success -> {
                    val list = ArrayList<CharactersUI>(charactersAdapter.currentList)
                    it.data.let { data -> list.addAll(data) }
                    charactersAdapter.submitList(list)
                    binding.progressBar.isVisible = false
                    binding.progressBarPage.isVisible = false
                }
            }
        }
    }


    override fun scrollListener() = with(binding) {
        rvCharacter.scrollListenNextPageCharacters(viewModel)
    }

    private fun setupListeners(id: Int, name: String) {
        findNavController().navigate(
            CharactersDirections.actionCharactersToDetailCharacter(
                name,id
            )
        )
    }

    private fun setupLongListeners(image: String) {
        findNavController().navigate(CharactersDirections.actionCharactersToDetailImage(image))
    }

}