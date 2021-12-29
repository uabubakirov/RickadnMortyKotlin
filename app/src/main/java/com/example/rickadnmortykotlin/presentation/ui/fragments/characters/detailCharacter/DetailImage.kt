package com.example.rickadnmortykotlin.presentation.ui.fragments.characters.detailCharacter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.rickadnmortykotlin.databinding.FragmentDetailImageBinding


class DetailImage : DialogFragment() {

    private val binding: FragmentDetailImageBinding by viewBinding()
    private val args:DetailImageArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    private fun getData() {
        binding.imgImage.load(args.image)
    }
}