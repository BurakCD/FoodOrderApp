package com.ethadien.yemeksiparisapp.data.repository

import com.ethadien.yemeksiparisapp.data.datasource.CartFoodDataSource
import com.google.firebase.auth.FirebaseAuth

class CartFoodRepository(var cartFoodDS : CartFoodDataSource) {

    suspend fun addToCart(
        food_name : String,
        food_image_name : String,
        food_price : Int,
        food_count : Int,
        user_name : String
    ) = cartFoodDS.save(food_name, food_image_name, food_price, food_count, user_name)

    suspend fun deleteFromCart(cart_food_id : Int, user_name : String)
    = cartFoodDS.delete(cart_food_id, user_name)

    suspend fun getCart(user_name : String) = cartFoodDS.getCart(user_name)!!
}