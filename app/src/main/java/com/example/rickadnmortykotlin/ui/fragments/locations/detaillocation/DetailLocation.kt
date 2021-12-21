package com.example.rickadnmortykotlin.ui.fragments.locations.detaillocation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.rickadnmortykotlin.common.base.BaseFragment
import com.example.rickadnmortykotlin.common.resource.Resource
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
            progressBar.isVisible = it is Resource.Loading
            group.isVisible = it !is Resource.Loading
            when(it){
                is Resource.Error -> {
                    Toast.makeText(requireContext(),it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {}
                is Resource.Success -> {
                    it.data?.let{data ->
                        txtType.text = data.type
                        txtName.text = data.name
                        txtDimension.text = data.dimension
                    }

                }
            }

        })
    }
}