package com.example.rickadnmortykotlin.presentation.ui.fragments.characters

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController

import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickadnmortykotlin.R

import com.example.rickadnmortykotlin.common.base.BaseFragment
import com.example.rickadnmortykotlin.data.network.dtos.characters.CharactersModel


import com.example.rickadnmortykotlin.databinding.FragmentCharactersBinding
import com.example.rickadnmortykotlin.presentation.ui.adapters.CharactersAdapter
import com.example.rickadnmortykotlin.presentation.ui.adapters.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Characters : BaseFragment<CharactersViewModel, FragmentCharactersBinding>(R.layout.fragment_characters) {

    private val charactersAdapter =
        CharactersAdapter(this::setupListeners, this::setupLongListeners)
    override val binding by viewBinding(FragmentCharactersBinding::bind)
    override val viewModel: CharactersViewModel by viewModels()


    override fun initialize() = with(binding) {
        rvCharacter.layoutManager = LinearLayoutManager(requireContext())
        rvCharacter.adapter = charactersAdapter.withLoadStateFooter(LoadStateAdapter {
            charactersAdapter.retry()
        })
        charactersAdapter.addLoadStateListener { loadStates ->
            rvCharacter.isVisible = loadStates.refresh is LoadState.NotLoading
            progressBar.isVisible = loadStates.refresh is LoadState.Loading
        }

        
    }

    override fun setupRequests() {
        viewLifecycleOwner.lifecycleScope.launch {
        viewModel.fetchCharacters().collectLatest{
            charactersAdapter.submitData(it)

            }
        }
    }


    override fun swipeRefresh()= with (binding) {
        swipeRefresh.setOnRefreshListener {
            charactersAdapter.refresh()
            Toast.makeText(requireContext(), "Обновлено", Toast.LENGTH_SHORT).show()
            swipeRefresh.isRefreshing = false
        }
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