package com.example.rickadnmortykotlin.ui.fragments.characters.detailCharacter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.rickadnmortykotlin.R
import com.example.rickadnmortykotlin.base.fragment.BaseFragment
import com.example.rickadnmortykotlin.databinding.FragmentDetailCharacterBinding
import com.example.rickadnmortykotlin.ui.fragments.characters.CharactersViewModel


class DetailCharacter : BaseFragment<CharactersViewModel, FragmentDetailCharacterBinding>(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailCharacterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun initialize() {
        viewModel = ViewModelProvider(requireActivity()).get(CharactersViewModel::class.java)
    }



    override fun setupObservers()= with(binding) {
        viewModel.getModel().observe(viewLifecycleOwner,{
            when(it.status){
                "Dead" -> imgIndicator.setImageResource(R.drawable.dead)
                "Alive" -> imgIndicator.setImageResource(R.drawable.alive)
                else -> imgIndicator.setImageResource(R.drawable.unknown)
            }
            txtName.text = it.name
            txtGender.text = it.gender
            txtSpecies.text = it.species
            txtStatus.text = it.status
            imgImage.load(it.image)
        })
    }
}