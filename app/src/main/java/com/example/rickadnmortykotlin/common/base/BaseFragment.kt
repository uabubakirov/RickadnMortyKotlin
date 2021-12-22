package com.example.rickadnmortykotlin.common.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.viewbinding.ViewBinding


abstract class BaseFragment<ViewModel : BaseViewModel, Binding : ViewBinding> : Fragment() {

    protected abstract val  binding: Binding
    protected abstract val  viewModel: ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupObservers()
        setupRequests()
        swipeRefresh()
    }


    open fun initialize() {}

    open fun setupObservers() {}

    open fun setupRequests() {}

    open fun swipeRefresh() {}


}