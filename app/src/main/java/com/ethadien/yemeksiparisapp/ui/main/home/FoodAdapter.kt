package com.ethadien.yemeksiparisapp.ui.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ethadien.yemeksiparisapp.data.entity.food.Food
import com.ethadien.yemeksiparisapp.databinding.FoodDesignBinding
import com.ethadien.yemeksiparisapp.utils.Constants.IMAGE_BASE_URL
import com.ethadien.yemeksiparisapp.utils.gate
import kotlin.random.Random

class FoodAdapter(
    val mContext : Context,
    var foodList: HashSet<Food>,
    var viewModel: HomeViewModel)
    : RecyclerView.Adapter<RecyclerViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding : FoodDesignBinding = FoodDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val food = foodList.elementAt(position)
        with(holder.binding){

            // Image Loader
                Glide.with(mContext)
                    .load("$IMAGE_BASE_URL${food.yemek_resim_adi}")
                    .override(110, 110)
                    .into(foodImage)

            // Variable Loaders
            foodInstance = food

            // Clicks
            cardView.setOnClickListener{
                val direction = HomeFragmentDirections.homeToDetails(food)
                Navigation.gate(it, direction)
            }
        }
    }

    override fun getItemCount(): Int {
        var itemPosition = 0
            itemPosition = Random.nextInt(0, foodList.size)
        return itemPosition
    }
}