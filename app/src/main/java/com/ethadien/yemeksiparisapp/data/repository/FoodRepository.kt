package com.ethadien.yemeksiparisapp.data.repository

import com.ethadien.yemeksiparisapp.data.datasource.FoodDataSource
import com.ethadien.yemeksiparisapp.data.entity.Food

class FoodRepository(var foodDS : FoodDataSource) {

    suspend fun getFoods() : List<Food> = foodDS.getFoods()
}