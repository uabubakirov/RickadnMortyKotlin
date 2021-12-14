package com.example.rickadnmortykotlin.ui.fragments.locations.detaillocation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.rickadnmortykotlin.base.fragment.BaseFragment
import com.example.rickadnmortykotlin.databinding.FragmentDetailLocationBinding
import com.example.rickadnmortykotlin.ui.fragments.locations.LocationViewModel


class DetailLocation : BaseFragment<LocationViewModel, FragmentDetailLocationBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailLocationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun initialize() {
        viewModel = ViewModelProvider(requireActivity()).get(LocationViewModel::class.java)
    }

    override fun setupObservers() = with(binding) {
        viewModel.getModel().observe(viewLifecycleOwner,{
            txtName.text = it.name
            txtType.text = it.type
            txtDimension.text = it.dimension

        })
    }

}