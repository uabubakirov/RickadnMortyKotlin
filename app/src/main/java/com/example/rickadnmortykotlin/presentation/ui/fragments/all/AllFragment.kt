package com.example.rickadnmortykotlin.presentation.ui.fragments.all

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickadnmortykotlin.R
import com.example.rickadnmortykotlin.common.base.BaseFragment
import com.example.rickadnmortykotlin.databinding.FragmentAllBinding
import com.example.rickadnmortykotlin.presentation.models.AllUI
import com.example.rickadnmortykotlin.presentation.state.UIState
import com.example.rickadnmortykotlin.presentation.ui.adapters.AllAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllFragment : BaseFragment<AllViewModel,FragmentAllBinding>(R.layout.fragment_all) {

    override val binding: FragmentAllBinding by viewBinding()
    override val viewModel: AllViewModel by viewModels()
    private val allAdapter = AllAdapter()
    private val lst: ArrayList<AllUI> = ArrayList()
    private var currentText = ""

    override fun initialize()= with(binding) {
        rvAll.layoutManager = LinearLayoutManager(requireContext())
        rvAll.adapter = allAdapter
    }

    override fun setupListeners() {
        searchAll()
    }

    private fun searchAll() {
        val handler = Handler(Looper.getMainLooper())
        val searchRunnable = Runnable {
            viewModel.fetchCharacterBySearch(currentText)
            viewModel.fetchLoc(currentText)
            viewModel.fetchEp(currentText)
        }
        binding.search.doAfterTextChanged {
            lst.clear()
            currentText = it?.toString() ?: ""
            handler.removeCallbacks(searchRunnable)
            handler.postDelayed(searchRunnable, 500L)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setupRequests() {
        viewModel.stateFilterEp.subscribe {
            when(it){
                    is UIState.Error -> {}
                    is UIState.Loading -> {}
                    is UIState.Success -> {
                        lst.addAll(it.data)
                        allAdapter.notifyDataSetChanged()
                        allAdapter.submitList(lst.sortedByDescending { data-> data.created })
                    }
                }
            }

            viewModel.stateFilterCharacters.subscribe {
                when(it){
                    is UIState.Error -> {
                        Log.e("an", "error", )
                    }
                    is UIState.Loading -> {
                        Log.e("an", "load", )
                    }
                    is UIState.Success -> {
                        lst.addAll(it.data)
                        allAdapter.notifyDataSetChanged()
                        allAdapter.submitList(lst.sortedByDescending { data-> data.created })
                    }
                }
            }

            viewModel.stateFilterLoc.subscribe {
                when(it){
                    is UIState.Error -> {}
                    is UIState.Loading -> {}
                    is UIState.Success -> {
                        lst.addAll(it.data)
                        allAdapter.notifyDataSetChanged()
                        allAdapter.submitList(lst.sortedByDescending { data-> data.created })
                    }
                }
            }
    }
}