package com.ethadien.yemeksiparisapp.retrofit

import com.ethadien.yemeksiparisapp.data.entity.food.FoodReesponse
import retrofit2.http.GET

interface FoodDao {

    @GET("tumYemekleriGetir.php")
    suspend fun getFoods() : FoodReesponse
}