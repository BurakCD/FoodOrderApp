package com.ethadien.yemeksiparisapp.ui.main.cart

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ethadien.yemeksiparisapp.R
import com.ethadien.yemeksiparisapp.data.entity.CartFood
import com.ethadien.yemeksiparisapp.databinding.CartDesignBinding
import com.ethadien.yemeksiparisapp.utils.Constants.IMAGE_BASE_URL
import com.ethadien.yemeksiparisapp.utils.showSnackbar

class CartFoodAdapter(
    var mContext : Context,
    var foodList : List<CartFood>,
    var viewModel: CartViewModel)
    : RecyclerView.Adapter<CartFoodAdapter.CartViewHolder>(){

        inner class CartViewHolder(binding: CartDesignBinding) : RecyclerView.ViewHolder(binding.root){
            var binding : CartDesignBinding
            init {
                this.binding = binding
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding : CartDesignBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext), R.layout.cart_design, parent, false)

        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val food = foodList.get(position)
        with(holder.binding){

            // Image Loader
            Glide.with(mContext)
                .load("$IMAGE_BASE_URL${food.food_image_name}")
                .override(90, 90)
                .into(foodImage)

            // Variable Loaders
            foodName.text = food.food_name
            foodPrice.text = food.food_price
            foodCount.text = food.food_count.toString()

            // Clicks
            deleteButton.setOnClickListener{

                val dialog = AlertDialog.Builder(mContext)
                dialog.setTitle(R.string.wantToDelete)
                dialog.setMessage(food.food_name)
                dialog.setIcon(R.drawable.ic_delete)

                dialog.setPositiveButton(R.string.okayText){ s, d-> // s = string d = Dialog Interface
                    val success = viewModel.delete(food.cart_food_id)

                    if (success.equals(1)){
                        showSnackbar(it, R.string.itemDeleted)
                    }else{
                        showSnackbar(it, R.string.itemCantDeleted)
                    }
                }
                dialog.setNegativeButton(R.string.noText){s, d ->
                }
                dialog.create().show()
            }
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }


}