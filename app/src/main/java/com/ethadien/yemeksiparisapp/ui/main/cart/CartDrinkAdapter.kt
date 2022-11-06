package com.ethadien.yemeksiparisapp.ui.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ethadien.yemeksiparisapp.R
import com.ethadien.yemeksiparisapp.data.entity.food.Food
import com.ethadien.yemeksiparisapp.databinding.FoodDesignBinding
import com.ethadien.yemeksiparisapp.ui.main.cart.CartViewModel
import com.ethadien.yemeksiparisapp.utils.Constants
import com.ethadien.yemeksiparisapp.utils.gate
import com.ethadien.yemeksiparisapp.utils.showSnackbar

class CartDrinkAdapter(var mContext : Context,
                       var foodList : HashSet<Food>,
                       var viewModel : CartViewModel
) : RecyclerView.Adapter<RecyclerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder
    {
        val binding = FoodDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int)
    {
        val food  = foodList.elementAt(position)
        with(holder.binding){

            foodInstance = food

            Glide.with(mContext)
                .load("${Constants.IMAGE_BASE_URL}${food.yemek_resim_adi}")
                .override(110, 110)
                .into(foodImage)

            cardView.setOnClickListener{
                val direction = HomeFragmentDirections.homeToDetails(food)
                Navigation.gate(it, direction)
            }

            addOne.setOnClickListener {
                viewModel.addOneToCart(food)
                showSnackbar(it, R.string.oneItemAdded)
                viewModel.getCart()
            }
        }
    }

    override fun getItemCount(): Int {
            return foodList.size
    }
}