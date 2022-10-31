package com.ethadien.yemeksiparisapp.retrofit

import com.ethadien.yemeksiparisapp.utils.Constants.BASE_URL

class ApiUtils {
    companion object{

        fun getFoodDao() : FoodDao{
            return RetrofitClient.getClient(BASE_URL).create(FoodDao::class.java)
        }

        fun getCartFoodDao() : CartFoodDao{
            return RetrofitClient.getClient(BASE_URL).create(CartFoodDao::class.java)
        }
    }
}