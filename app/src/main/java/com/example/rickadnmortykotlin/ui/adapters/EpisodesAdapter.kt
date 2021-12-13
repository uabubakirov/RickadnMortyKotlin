package com.example.rickadnmortykotlin.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickadnmortykotlin.base.adapter.BaseComparator
import com.example.rickadnmortykotlin.data.network.dtos.episodes.EpisodesModel
import com.example.rickadnmortykotlin.databinding.EpisodeItemsBinding
import com.example.rickadnmortykotlin.utils.OnItemClick

class EpisodesAdapter: PagingDataAdapter<EpisodesModel, EpisodesAdapter.EpisodesViewHolder>(
    BaseComparator()
) {

    private lateinit var onItemClick: OnItemClick

    fun onItemClick(click:OnItemClick){
        onItemClick = click
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onFill(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        return EpisodesViewHolder(EpisodeItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    inner class EpisodesViewHolder(private val binding:EpisodeItemsBinding):RecyclerView.ViewHolder(binding.root) {
        fun onFill(s: EpisodesModel)= with(binding) {
            txtName.text = s.name
            root.setOnClickListener {
                onItemClick.onItemCLickEpisode(s,s.name)
            }
        }

    }
}