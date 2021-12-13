package com.example.rickadnmortykotlin.ui.fragments.characters.detailCharacter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.rickadnmortykotlin.databinding.FragmentDetailImageBinding
import com.example.rickadnmortykotlin.ui.fragments.characters.CharactersViewModel


class DetailImage : DialogFragment() {

    private lateinit var binding:FragmentDetailImageBinding
    private lateinit var viewModel:CharactersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailImageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        getData()
    }

    private fun initialize() {
        viewModel = ViewModelProvider(requireActivity()).get(CharactersViewModel::class.java)
    }

    private fun getData() {
        viewModel.getModel().observe(viewLifecycleOwner,{
            binding.imgImage.load(it.image)
        })
    }
}