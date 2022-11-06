package com.ethadien.yemeksiparisapp.data.datasource

import com.ethadien.yemeksiparisapp.data.entity.food.Food
import com.ethadien.yemeksiparisapp.retrofit.FoodDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodDataSource(var foodDao : FoodDao) {

    suspend fun getFoods() : HashSet<Food> =
        withContext(Dispatchers.IO){
            foodDao.getFoods().yemekler
        }
}