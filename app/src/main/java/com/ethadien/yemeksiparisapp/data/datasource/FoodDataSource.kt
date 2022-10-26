package com.ethadien.yemeksiparisapp.data.datasource

import com.ethadien.yemeksiparisapp.data.entity.Food
import com.ethadien.yemeksiparisapp.retrofit.FoodDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodDataSource(var foodDao : FoodDao) {

    suspend fun getFoods() : List<Food> =
        withContext(Dispatchers.IO){
            foodDao.getFoods().foods
        }
}