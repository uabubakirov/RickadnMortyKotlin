package com.example.rickadnmortykotlin.presentation.ui.fragments.characters

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickadnmortykotlin.R
import com.example.rickadnmortykotlin.common.base.BaseFragment
import com.example.rickadnmortykotlin.common.extension.scrollListenNextPageCharacters
import com.example.rickadnmortykotlin.common.extension.showToast
import com.example.rickadnmortykotlin.databinding.FragmentCharactersBinding
import com.example.rickadnmortykotlin.presentation.models.CharactersUI
import com.example.rickadnmortykotlin.presentation.models.FilterData
import com.example.rickadnmortykotlin.presentation.state.UIState
import com.example.rickadnmortykotlin.presentation.ui.adapters.CharactersAdapter
import com.example.rickadnmortykotlin.presentation.ui.fragments.characters.detailCharacter.DetailImageArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@AndroidEntryPoint
class Characters : BaseFragment<CharactersViewModel, FragmentCharactersBinding>(R.layout.fragment_characters) {

    private val charactersAdapter =
        CharactersAdapter(this::clickCharacter, this::setupLongListeners)
    override val binding by viewBinding(FragmentCharactersBinding::bind)
    override val viewModel: CharactersViewModel by viewModels()
    private var filterData: FilterData? = FilterData()
    private var currentText = ""

    override fun initialize() = with(binding) {
        rvCharacter.layoutManager = LinearLayoutManager(requireContext())
        rvCharacter.adapter = charactersAdapter
        filterData = arguments?.getParcelable("data")
    }

    override fun setupRequests() {
        Log.d("anime", "setupRequests: $filterData")
        if(filterData == null){
        viewModel.stateCharacters.subscribe {
                    when (it) {
                    is UIState.Error -> {
                    }
                    is UIState.Loading -> {
                        if (viewModel.count == 0) {
                            binding.progressBar.isVisible = true
                            viewModel.count++
                        } else {
                            binding.progressBarPage.isVisible = true
                        }
                    }

                    is UIState.Success -> {
                        val list = ArrayList<CharactersUI>(charactersAdapter.currentList)
                        it.data.let { data -> list.addAll(data) }
                        charactersAdapter.submitList(list)
                        binding.progressBar.isVisible = false
                        binding.progressBarPage.isVisible = false
                    }
                }
            }}else{
                viewModel.fetchCharactersByGenderAndStatus(filterData?.gender,viewModel.page,filterData?.status)
                viewModel.stateFilterCharacters.subscribe {
                    when(it){
                        is UIState.Error -> {showToast("error")}
                        is UIState.Loading -> {showToast("load")}
                        is UIState.Success -> {
                            val list = ArrayList<CharactersUI>(charactersAdapter.currentList)
                            it.data.let { data -> list.addAll(data) }
                            charactersAdapter.submitList(list)
                        }
                    }
                }
        }

    }

    override fun scrollListener() = with(binding) {
        rvCharacter.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if(filterData != null){
                        viewModel.page++
                        viewModel.fetchCharactersByGenderAndStatus(filterData?.gender,viewModel.page,filterData?.status)
                        Log.d("filter", "${viewModel.page}")
                    }else{
                        viewModel.page++
                        viewModel.fetchCharacters(viewModel.page)
                        Log.d("prost", "${viewModel.page}")
                    }


                }
            }
        })
    }

    override fun setupListeners() {
//        search()
        binding.btnSave.setOnClickListener {
            findNavController().navigate(R.id.characterFilter2)
        }
    }

//    private fun search() = with(binding) {
//        val handler = Handler(Looper.getMainLooper())
//        val searchRunnable = Runnable {
//            viewModel.fetchCharacterBySearch(currentText)
//        }
//        binding.seatch.doAfterTextChanged {
//            currentText = it?.toString() ?: ""
//            handler.removeCallbacks(searchRunnable)
//            handler.postDelayed(searchRunnable, 500L)
//        }
//
//        viewModel.stateFilterCharacters.subscribe {
//            when(it){
//                is UIState.Error -> {}
//                is UIState.Loading -> {
//                    }
//                is UIState.Success -> {
//                    charactersAdapter.submitList(it.data)
//                }
//            }
//        }
//    }

    private fun clickCharacter(id: Int, name: String) {
        findNavController().navigate(
            CharactersDirections.actionCharactersToDetailCharacter(
                name,id
            )
        )
    }

    private fun setupLongListeners(image: String) {
        findNavController().navigate(CharactersDirections.actionCharactersToDetailImage(image))
    }

}
