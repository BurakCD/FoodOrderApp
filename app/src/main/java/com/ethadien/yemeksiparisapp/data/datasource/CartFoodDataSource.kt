package com.ethadien.yemeksiparisapp.data.datasource

import com.ethadien.yemeksiparisapp.retrofit.CartFoodDao

class CartFoodDataSource(var cartFoodDao: CartFoodDao) {

    suspend fun save(
        food_name : String,
        food_image_name : String,
        food_price : Int,
        food_count : Int,
        user_name : String
    ) = cartFoodDao.save(food_name, food_image_name, food_price, food_count, user_name).success

    suspend fun getCart(
        user_name : String
    ) = cartFoodDao.getCart(user_name).cart

    suspend fun delete(
        cart_food_id : Int,
        user_name : String
    ) = cartFoodDao.delete(cart_food_id, user_name).success
}