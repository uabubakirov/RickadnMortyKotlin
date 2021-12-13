package com.example.rickadnmortykotlin.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickadnmortykotlin.base.adapter.BaseComparator
import com.example.rickadnmortykotlin.data.network.dtos.locations.LocationsModel
import com.example.rickadnmortykotlin.databinding.LocationItemsBinding
import com.example.rickadnmortykotlin.utils.OnItemClick

class LocationsAdapter : PagingDataAdapter<LocationsModel, LocationsAdapter.LocationsViewHolder>(
    BaseComparator()
) {

    private lateinit var onItemClick:OnItemClick

    fun onItemCLick(click:OnItemClick){
        onItemClick = click
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        getItem(position)?.let {
                holder.onFill(it)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        return LocationsViewHolder(LocationItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

   inner class LocationsViewHolder(private val binding: LocationItemsBinding):RecyclerView.ViewHolder(binding.root) {
       fun onFill(s: LocationsModel) = with(binding) {
           txtName.text = s.name
           root.setOnClickListener{
               onItemClick.onItemClickLocation(s,s.name)
           }
       }

   }

}