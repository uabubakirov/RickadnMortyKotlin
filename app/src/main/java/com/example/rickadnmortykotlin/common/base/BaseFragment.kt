package com.example.rickadnmortykotlin.common.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.example.rickadnmortykotlin.presentation.state.UIState
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


abstract class BaseFragment<ViewModel : BaseViewModel, viewBinding : ViewBinding>(
    @LayoutRes layoutId: Int
) : Fragment(layoutId) {

    protected abstract val binding: viewBinding
    protected abstract val viewModel: ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupObservers()
        setupRequests()
        scrollListener()
        setupListeners()
    }

    open fun initialize() {}

    open fun setupObservers() {}

    open fun setupListeners(){}

    open fun setupRequests() {}

    open fun scrollListener() {}

    open fun <T> StateFlow<UIState<T>>.subscribe(
        state: Lifecycle.State = Lifecycle.State.STARTED,
        action: (UIState<T>) -> Unit
    ){
        viewLifecycleOwner.lifecycleScope.async {
            viewLifecycleOwner.repeatOnLifecycle(state){
                this@subscribe.collect{
                    action(it)
                }
            }
        }
    }
}