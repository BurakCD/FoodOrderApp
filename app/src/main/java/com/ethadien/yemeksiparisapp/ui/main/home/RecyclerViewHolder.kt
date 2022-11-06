package com.ethadien.yemeksiparisapp.ui.main.home

import androidx.recyclerview.widget.RecyclerView
import com.ethadien.yemeksiparisapp.databinding.FoodDesignBinding

class RecyclerViewHolder(binding: FoodDesignBinding) : RecyclerView.ViewHolder(binding.root)
{
    var binding : FoodDesignBinding
    init {
        this.binding = binding
    }
}