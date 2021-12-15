package com.example.rickadnmortykotlin.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickadnmortykotlin.base.adapter.BaseComparator
import com.example.rickadnmortykotlin.data.network.dtos.locations.LocationsModel
import com.example.rickadnmortykotlin.databinding.LocationItemsBinding


class LocationsAdapter(
    private val onItemClick:(id: Int,name:String)->Unit,
) : PagingDataAdapter<LocationsModel, LocationsAdapter.LocationsViewHolder>(
    BaseComparator()
) {


    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        getItem(position)?.let {
                holder.onFill(it)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        return LocationsViewHolder(LocationItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

   inner class LocationsViewHolder(private val binding: LocationItemsBinding):RecyclerView.ViewHolder(binding.root) {

       init {
           itemView.setOnClickListener {
               getItem(absoluteAdapterPosition)?.let {
                   onItemClick(it.id,it.name)
               }
           }}

       fun onFill(s: LocationsModel) = with(binding) {
           txtName.text = s.name
       }

   }

}