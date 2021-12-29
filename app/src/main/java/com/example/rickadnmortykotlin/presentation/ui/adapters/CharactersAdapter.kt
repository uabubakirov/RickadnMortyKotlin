package com.example.rickadnmortykotlin.presentation.ui.adapters

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickadnmortykotlin.common.base.BaseComparator
import com.example.rickadnmortykotlin.data.network.dtos.characters.CharactersModel
import com.example.rickadnmortykotlin.databinding.CharacterItemsBinding


class CharactersAdapter(
    private val onItemClick:(id: Int,name: String)-> Unit,
    private val onLongClickListener:(image:String)-> Unit
                        ): PagingDataAdapter<CharactersModel,CharactersAdapter.ViewHolder>(
    BaseComparator()
) {



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
        init {
            itemView.setOnClickListener{
                getItem(absoluteAdapterPosition)?.let {
                    onItemClick(it.id,it.name)
                }
            }
            itemView.setOnLongClickListener{
                getItem(absoluteAdapterPosition)?.let {
                    onLongClickListener(it.image)
                }
                false
            }
        }

        fun onFill(s: CharactersModel)= with(binding) {
            txtName.text = s.name
            imgImage.load(s.image)
        }

    }

}