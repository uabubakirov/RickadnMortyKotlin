package com.example.rickadnmortykotlin.ui.fragments.locations.detaillocation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.rickadnmortykotlin.base.fragment.BaseFragment
import com.example.rickadnmortykotlin.databinding.FragmentDetailLocationBinding
import com.example.rickadnmortykotlin.ui.fragments.locations.LocationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailLocation : BaseFragment<LocationViewModel, FragmentDetailLocationBinding>() {

    override lateinit var binding: FragmentDetailLocationBinding
    override val viewModel: LocationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailLocationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun setupObservers() = with(binding) {
        viewModel.fetchLocation(DetailLocationArgs.fromBundle(requireArguments()).id).observe(viewLifecycleOwner,{
            txtType.text = it.type
            txtName.text = it.name
            txtDimension.text = it.dimension
        })
    }




}