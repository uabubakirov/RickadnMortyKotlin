package com.example.rickadnmortykotlin.presentation.ui.fragments.characters.filter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickadnmortykotlin.R
import com.example.rickadnmortykotlin.common.extension.getText
import com.example.rickadnmortykotlin.common.extension.showToast
import com.example.rickadnmortykotlin.databinding.CharacterFilterBinding
import com.example.rickadnmortykotlin.presentation.models.FilterData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFilter : DialogFragment() {

    private var _binding: CharacterFilterBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = CharacterFilterBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(activity)
            .setView(binding.root)
            .create()
        builder.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        getData()
        setupListeners()
        return builder
    }

    private fun setupListeners() {
        resetFilter()
    }

    private fun resetFilter() {
        binding.btnReset.setOnClickListener{
            findNavController().navigate(R.id.characters)
        }
    }

    private fun getData() = with(binding) {
        btnSave.setOnClickListener {
            val genderText = groupGender.getText()
            val statusText = groupStatus.getText()
            val filterData = FilterData(status = statusText,gender = genderText)
            val bundle = Bundle()
            bundle.putParcelable("data",filterData)
            findNavController().navigate(R.id.characters,bundle)


        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}