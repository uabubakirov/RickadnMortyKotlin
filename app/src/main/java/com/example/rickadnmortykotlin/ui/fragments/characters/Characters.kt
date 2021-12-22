package com.example.rickadnmortykotlin.ui.fragments.characters

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
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.rickadnmortykotlin.common.base.BaseFragment


import com.example.rickadnmortykotlin.databinding.FragmentCharactersBinding
import com.example.rickadnmortykotlin.ui.adapters.CharactersAdapter
import com.example.rickadnmortykotlin.ui.adapters.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Characters : BaseFragment<CharactersViewModel, FragmentCharactersBinding>() {

    private val charactersAdapter =
        CharactersAdapter(this::setupListeners, this::setupLongListeners)
    override lateinit var binding: FragmentCharactersBinding
    override val viewModel: CharactersViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }


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
        viewModel.fetchCharacters().observe(viewLifecycleOwner, {
            viewLifecycleOwner.lifecycleScope.launch {
                charactersAdapter.submitData(it)
            }
        })
    }

    override fun swipeRefresh() = with(binding) {
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