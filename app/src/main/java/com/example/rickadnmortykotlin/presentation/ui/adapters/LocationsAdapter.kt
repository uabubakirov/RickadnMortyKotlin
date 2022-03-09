package com.example.rickadnmortykotlin.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickadnmortykotlin.common.base.BaseComparator

import com.example.rickadnmortykotlin.databinding.LocationItemsBinding
import com.example.rickadnmortykotlin.presentation.models.LocationUI


class LocationsAdapter(
    private val onItemClick:(id: Int,name:String)->Unit,
) : ListAdapter<LocationUI, LocationsAdapter.LocationsViewHolder>(
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
                   onItemClick(it.id, it.name)
               }
           }
       }

       fun onFill(s: LocationUI) = with(binding) {
           txtName.text = s.name
       }
   }
}