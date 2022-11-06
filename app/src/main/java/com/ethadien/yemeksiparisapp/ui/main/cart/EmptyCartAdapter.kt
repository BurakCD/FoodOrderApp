package com.ethadien.yemeksiparisapp.ui.main.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ethadien.yemeksiparisapp.databinding.EmptyCartViewBinding

class EmptyCartAdapter(var mContext : Context)
    : RecyclerView.Adapter<EmptyCartAdapter.EmptyCartViewHolder>(){

    inner class EmptyCartViewHolder(binding: EmptyCartViewBinding) : RecyclerView.ViewHolder(binding.root){
        var binding : EmptyCartViewBinding
        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmptyCartViewHolder {
        val binding : EmptyCartViewBinding = EmptyCartViewBinding.inflate(LayoutInflater.from(mContext), parent, false)

        return EmptyCartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmptyCartViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 1
    }
}