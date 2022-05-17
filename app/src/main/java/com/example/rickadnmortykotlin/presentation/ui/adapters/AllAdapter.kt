package com.example.rickadnmortykotlin.presentation.ui.adapters

import android.location.Location
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickadnmortykotlin.R
import com.example.rickadnmortykotlin.common.base.BaseComparator
import com.example.rickadnmortykotlin.databinding.CharacterItemsBinding
import com.example.rickadnmortykotlin.databinding.EpisodeItemsBinding
import com.example.rickadnmortykotlin.databinding.LocationItemsBinding
import com.example.rickadnmortykotlin.presentation.models.AllUI
import com.example.rickadnmortykotlin.presentation.models.CharactersUI

class AllAdapter : ListAdapter<AllUI,RecyclerView.ViewHolder>(BaseComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            R.layout.character_items -> CharacterViewHolder(CharacterItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            R.layout.episode_items -> EpisodeViewHolder(EpisodeItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            R.layout.location_items -> LocationViewHolder(LocationItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            else -> throw IllegalArgumentException("View type not found: $viewType")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)){
            R.layout.character_items ->getItem(position)?.let {
                (holder as CharacterViewHolder).onBind(it)
            }
            R.layout.episode_items ->getItem(position)?.let {
                (holder as EpisodeViewHolder).onBind(it)
            }
            R.layout.location_items ->getItem(position)?.let {
                (holder as LocationViewHolder).onBind(it)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when{
            getItem(position).air_date != null -> {
                R.layout.episode_items
            }
            getItem(position).dimension != null -> {
                R.layout.location_items
            }
            else -> {
                R.layout.character_items
            }
        }
    }


    inner class CharacterViewHolder(private val binding: CharacterItemsBinding) : RecyclerView.ViewHolder(binding.root){

        fun onBind(s: AllUI){
            binding.txtName.text = s.name
            binding.imgImage.load(s.image)
        }
    }


    inner class EpisodeViewHolder(private val binding: EpisodeItemsBinding) : RecyclerView.ViewHolder(binding.root){

        fun onBind(s: AllUI){
            binding.txtName.text = s.name
        }
    }

    inner class LocationViewHolder(private val binding: LocationItemsBinding) : RecyclerView.ViewHolder(binding.root){

        fun onBind(s: AllUI){
            binding.txtName.text = s.name
        }
    }



}