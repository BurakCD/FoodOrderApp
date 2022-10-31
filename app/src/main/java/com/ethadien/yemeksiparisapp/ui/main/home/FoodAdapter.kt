package com.ethadien.yemeksiparisapp.ui.main.home

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ethadien.yemeksiparisapp.R
import com.ethadien.yemeksiparisapp.data.entity.Food
import com.ethadien.yemeksiparisapp.databinding.CartDesignBinding
import com.ethadien.yemeksiparisapp.databinding.FoodDesignBinding
import com.ethadien.yemeksiparisapp.ui.main.cart.CartViewModel
import com.ethadien.yemeksiparisapp.utils.Constants
import com.ethadien.yemeksiparisapp.utils.gate
import com.ethadien.yemeksiparisapp.utils.showSnackbar

class FoodAdapter(
    val mContext : Context,
    var foodList: List<Food>,
    var viewModel: HomeViewModel)
    : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>(){

        inner class FoodViewHolder(binding: FoodDesignBinding) : RecyclerView.ViewHolder(binding.root){
            var binding : FoodDesignBinding
            init {
                this.binding = binding
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding : FoodDesignBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext), R.layout.food_design, parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList.get(position)
        with(holder.binding){

            // Image Loader
            Glide.with(mContext)
                .load("${Constants.IMAGE_BASE_URL}${food.food_image_name}")
                .override(90, 90)
                .into(foodImage)

            // Variable Loaders
            foodName.text = food.food_name

            // Clicks
            cardView.setOnClickListener{
                val direction = HomeFragmentDirections.homeToDetails(food)
                Navigation.gate(it, direction)
            }

        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }


}