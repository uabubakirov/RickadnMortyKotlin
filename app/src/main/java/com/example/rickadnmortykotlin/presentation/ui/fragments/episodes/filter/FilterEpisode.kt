package com.example.rickadnmortykotlin.presentation.ui.fragments.episodes.filter

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
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickadnmortykotlin.R
import com.example.rickadnmortykotlin.databinding.CharacterFilterBinding
import com.example.rickadnmortykotlin.databinding.FragmentFilterEpisodeBinding
import com.example.rickadnmortykotlin.presentation.models.FilterData


class FilterEpisode : DialogFragment() {

    private var _binding: FragmentFilterEpisodeBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentFilterEpisodeBinding.inflate(LayoutInflater.from(context))
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
            findNavController().navigate(R.id.episodes)
        }
    }

    private fun getData() = with(binding) {
            save.setOnClickListener {
                val filterData = FilterData(param1 = etSearchEpisode.text.toString(),param2 = etSearchName.text.toString())
                val bundle = Bundle()
                bundle.putParcelable("data",filterData)
                findNavController().navigate(R.id.episodes,bundle)
            }
        }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}