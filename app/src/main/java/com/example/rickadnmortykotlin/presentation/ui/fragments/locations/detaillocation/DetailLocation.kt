package com.example.rickadnmortykotlin.presentation.ui.fragments.locations.detaillocation


import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickadnmortykotlin.R
import com.example.rickadnmortykotlin.common.base.BaseFragment
import com.example.rickadnmortykotlin.common.resource.Resource
import com.example.rickadnmortykotlin.databinding.FragmentDetailLocationBinding
import com.example.rickadnmortykotlin.presentation.state.UIState
import com.example.rickadnmortykotlin.presentation.ui.fragments.locations.LocationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailLocation : BaseFragment<LocationViewModel, FragmentDetailLocationBinding>(R.layout.fragment_detail_location) {

    override val binding: FragmentDetailLocationBinding by viewBinding()
    override val viewModel: LocationViewModel by viewModels()

    override fun initialize() {
        viewModel.fetchLocation(DetailLocationArgs.fromBundle(requireArguments()).id)
    }

    override fun setupObservers() = with(binding) {
        viewModel.dataLocations.subscribe {
            when(it){
                is UIState.Error -> {}
                is UIState.Loading -> {}
                is UIState.Success -> {
                    txtName.text = it.data.name
                    txtDimension.text = it.data.dimension
                    txtType.text = it.data.type

                }
            }
        }
    }
}
