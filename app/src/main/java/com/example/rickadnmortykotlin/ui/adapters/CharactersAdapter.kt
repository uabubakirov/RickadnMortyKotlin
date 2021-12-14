package com.example.rickadnmortykotlin.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickadnmortykotlin.base.adapter.BaseComparator
import com.example.rickadnmortykotlin.data.network.dtos.characters.CharactersModel
import com.example.rickadnmortykotlin.databinding.CharacterItemsBinding
import com.example.rickadnmortykotlin.utils.OnItemClick
import com.example.rickadnmortykotlin.utils.OnItemLongClick

class CharactersAdapter: PagingDataAdapter<CharactersModel,CharactersAdapter.ViewHolder>(
    BaseComparator()
) {

    private lateinit var onItemClick: OnItemClick
    private lateinit var onItemLongClick: OnItemLongClick

    fun itemClick(onItemClick2: OnItemClick){
        onItemClick = onItemClick2

    }
    fun itemLongClick(onItemLongClick2: OnItemLongClick){
        onItemLongClick = onItemLongClick2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CharacterItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.onFill(it) }
    }

    inner class ViewHolder(private val binding: CharacterItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onFill(s: CharactersModel)= with(binding) {
            txtName.text = s.name
            imgImage.load(s.image)
            root.setOnClickListener{
                onItemClick.onItemCLick(s,s.name)
            }
            root.setOnLongClickListener {
                onItemLongClick.onItemLongCLick(s)
                false
            }

        }

    }

}