package com.example.rickadnmortykotlin.ui.fragments.characters.detailCharacter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.rickadnmortykotlin.R
import com.example.rickadnmortykotlin.common.base.BaseFragment
import com.example.rickadnmortykotlin.common.resource.Resource
import com.example.rickadnmortykotlin.databinding.FragmentDetailCharacterBinding
import com.example.rickadnmortykotlin.databinding.FragmentDetailEpisodeBinding
import com.example.rickadnmortykotlin.ui.fragments.characters.CharactersViewModel
import com.example.rickadnmortykotlin.ui.fragments.episodes.EpisodeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCharacter : BaseFragment<CharactersViewModel,FragmentDetailCharacterBinding>(){

    override lateinit var binding: FragmentDetailCharacterBinding
    override val viewModel: CharactersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailCharacterBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun setupRequests() = with(binding) {
        viewModel.fetchCharacter(DetailCharacterArgs.fromBundle(requireArguments()).id).observe(viewLifecycleOwner,{
            progressBar.isVisible = it is Resource.Loading
            group.isVisible = it !is Resource.Loading
            when(it){
                is Resource.Error -> {
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    it.data?.let { data ->
                        when(data.status){
                            "Alive" -> imgIndicator.setImageResource(R.drawable.alive)
                            "Dead" -> imgIndicator.setImageResource(R.drawable.dead)
                            else -> imgIndicator.setImageResource(R.drawable.unknown)
                        }
                        txtName.text = data.name
                        txtGender.text = data.gender
                        txtSpecies.text = data.species
                        txtStatus.text = data.status
                        imgImage.load(data.image)

                    }
                }
            }
        })

    }

}