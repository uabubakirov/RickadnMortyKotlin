package com.example.rickadnmortykotlin.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickadnmortykotlin.common.base.BaseComparator
import com.example.rickadnmortykotlin.data.network.dtos.episodes.EpisodesModelDTO
import com.example.rickadnmortykotlin.databinding.EpisodeItemsBinding
import com.example.rickadnmortykotlin.presentation.models.EpisodesUI


class EpisodesAdapter(
    private val onItemClick:(id: Int,name: String)->Unit
): ListAdapter<EpisodesUI, EpisodesAdapter.EpisodesViewHolder>(
    BaseComparator()
) {

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onFill(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        return EpisodesViewHolder(EpisodeItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    inner class EpisodesViewHolder(private val binding:EpisodeItemsBinding):RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let {
                    onItemClick(it.id,it.name)
                }
            }}

        fun onFill(s: EpisodesUI) = with(binding) {
            txtName.text = s.name
        }
    }
}