package com.example.rickadnmortykotlin.ui.fragments.characters.detailCharacter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import coil.load
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

    override fun setupObservers() {
        viewModel.getModel().observe(viewLifecycleOwner,{
            binding.txtName.text = it.name
            binding.txtGender.text = it.gender
            binding.txtSpecies.text = it.species
            binding.txtStatus.text = it.status
            binding.imgImage.load(it.image)
        })
    }
}