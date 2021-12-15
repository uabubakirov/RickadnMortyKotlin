package com.example.rickadnmortykotlin.ui.fragments.characters.detailCharacter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import coil.load
import com.example.rickadnmortykotlin.R
import com.example.rickadnmortykotlin.base.fragment.BaseFragment
import com.example.rickadnmortykotlin.databinding.FragmentDetailCharacterBinding
import com.example.rickadnmortykotlin.ui.fragments.characters.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCharacter : BaseFragment<CharactersViewModel, FragmentDetailCharacterBinding>(){

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

    override fun setupObservers() = with(binding){
        viewModel.fetchCharacter(DetailCharacterArgs.fromBundle(requireArguments()).id).observe(viewLifecycleOwner,{
            when(it.status){
                "Alive" -> imgIndicator.setImageResource(R.drawable.alive)
                "Dead" -> imgIndicator.setImageResource(R.drawable.dead)
                else -> imgIndicator.setImageResource(R.drawable.unknown)
            }
            txtName.text = it.name
            imgImage.load(it.image)
            txtStatus.text = it.status
            txtSpecies.text = it.species
            txtGender.text = it.gender
        })

    }



}