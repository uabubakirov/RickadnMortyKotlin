package com.example.rickadnmortykotlin.presentation.ui.adapters.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.rickadnmortykotlin.databinding.ItemLoadStateFooterViewBinding

class LoadStateViewHolder(
    private val binding: ItemLoadStateFooterViewBinding,
    retry:()->Unit
):RecyclerView.ViewHolder(binding.root) {
    init {
        binding.retry.setOnClickListener{
            retry.invoke()
        }
    }
    fun bind(loadState : LoadState){
        if (loadState is LoadState.Error){
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retry.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
    }
    companion object{
        fun create(parent: ViewGroup,retry: () -> Unit):LoadStateViewHolder{
            return LoadStateViewHolder(
                ItemLoadStateFooterViewBinding.inflate(
                    LayoutInflater.from(parent.context),parent,false
                ),
                retry
            )
        }
    }
}