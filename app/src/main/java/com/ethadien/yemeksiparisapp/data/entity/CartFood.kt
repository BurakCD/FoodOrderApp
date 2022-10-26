package com.ethadien.yemeksiparisapp.data.entity

import java.io.Serializable

data class CartFood(
    var cart_food_id : Int,
    var food_name : String,
    var food_image_name : String,
    var food_price : String,
    var food_count : Int,
    var user_name : String
) : Serializable {}