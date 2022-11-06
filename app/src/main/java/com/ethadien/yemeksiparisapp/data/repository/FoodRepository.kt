package com.ethadien.yemeksiparisapp.data.repository

import com.ethadien.yemeksiparisapp.data.datasource.FoodDataSource
import com.ethadien.yemeksiparisapp.data.entity.food.Food

class FoodRepository(var foodDS : FoodDataSource) {

    suspend fun getFoods() : HashSet<Food> = foodDS.getFoods()
}