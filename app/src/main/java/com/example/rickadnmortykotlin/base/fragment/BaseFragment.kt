package com.example.rickadnmortykotlin.base.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.viewbinding.ViewBinding


abstract class BaseFragment<ViewModel: BaseViewModel,Binding:ViewBinding> : Fragment() {

    protected lateinit var binding:Binding
    protected lateinit var viewModel: ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
        setupObservers()
        setupRequests()
        swipeRefresh()
    }

    open protected fun setupObservers(){}

    open protected fun setupRequests(){}

    open protected fun setupListeners(){}

    open protected fun initialize() {}

    open protected fun swipeRefresh(){}

}