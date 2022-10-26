package com.ethadien.yemeksiparisapp.retrofit

class ApiUtils {
    companion object{
        var BASE_URL = "http://kasimadalan.pe.hu/yemekler/"

        fun getFoodDao() : FoodDao{
            return RetrofitClient.getClient(BASE_URL).create(FoodDao::class.java)
        }

        fun getCartFoodDao() : CartFoodDao{
            return RetrofitClient.getClient(BASE_URL).create(CartFoodDao::class.java)
        }
    }
}