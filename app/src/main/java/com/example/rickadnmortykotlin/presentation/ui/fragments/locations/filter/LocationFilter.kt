package com.example.rickadnmortykotlin.presentation.ui.fragments.locations.filter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.rickadnmortykotlin.R
import com.example.rickadnmortykotlin.databinding.FragmentFilterEpisodeBinding
import com.example.rickadnmortykotlin.databinding.FragmentLocationFilterBinding
import com.example.rickadnmortykotlin.presentation.models.FilterData


class LocationFilter : DialogFragment() {

    private var _binding: FragmentLocationFilterBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentLocationFilterBinding.inflate(LayoutInflater.from(context))
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
        binding.reset.setOnClickListener{
            findNavController().navigate(R.id.locations)
        }
    }

    private fun getData() = with(binding) {
        save.setOnClickListener {
            val filterData = FilterData(param1 = etSearchName.text.toString(),param2 = etSearchType.text.toString(),param3 = etSearchDimension.text.toString())
            val bundle = Bundle()
            bundle.putParcelable("data",filterData)
            findNavController().navigate(R.id.locations,bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}