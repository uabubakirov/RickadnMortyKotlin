package com.example.rickadnmortykotlin.ui.fragments.characters

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickadnmortykotlin.R

import com.example.rickadnmortykotlin.base.fragment.BaseFragment
import com.example.rickadnmortykotlin.data.network.dtos.characters.CharactersModel
import com.example.rickadnmortykotlin.data.network.dtos.episodes.EpisodesModel
import com.example.rickadnmortykotlin.data.network.dtos.locations.LocationsModel


import com.example.rickadnmortykotlin.databinding.FragmentCharactersBinding
import com.example.rickadnmortykotlin.ui.adapters.CharactersAdapter
import com.example.rickadnmortykotlin.ui.adapters.paging.LoadStateAdapter
import com.example.rickadnmortykotlin.utils.OnItemClick
import com.example.rickadnmortykotlin.utils.OnItemLongClick
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class Characters : BaseFragment<CharactersViewModel, FragmentCharactersBinding>() {

    private val charactersAdapter = CharactersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun initialize() {
        viewModel = ViewModelProvider(requireActivity()).get(CharactersViewModel::class.java)
        binding.rvCharacter.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCharacter.adapter = charactersAdapter.withLoadStateFooter(LoadStateAdapter{
            charactersAdapter.retry()
        })
    }

    override fun setupRequests() {
        viewModel.fetchCharacters().observe(viewLifecycleOwner, {
            viewLifecycleOwner.lifecycleScope.launch {
                charactersAdapter.submitData(it)
            }
        })
    }

    override fun swipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            Toast.makeText(requireContext(),"Обновлено",Toast.LENGTH_SHORT).show()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    override fun setupListeners() {
        charactersAdapter.itemLongClick(object :OnItemLongClick{
            override fun onItemLongCLick(character: CharactersModel) {
                viewModel.selectModel(character)
                Navigation.findNavController(requireView()).navigate(R.id.detailImage)
            }

        })
        charactersAdapter.itemClick(object :OnItemClick{
            override fun onItemCLick(character: CharactersModel, name: String) {
                viewModel.selectModel(character)
                Navigation.findNavController(requireView()).navigate(CharactersDirections.actionCharactersToDetailCharacter(name))
            }

            override fun onItemCLickEpisode(episode: EpisodesModel, name: String) {
                TODO("Not yet implemented")
            }

            override fun onItemClickLocation(location: LocationsModel, name: String) {
                TODO("Not yet implemented")
            }


        })
    }
}