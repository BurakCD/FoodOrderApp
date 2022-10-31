package com.ethadien.yemeksiparisapp.ui.main.detail

import androidx.lifecycle.ViewModel
import com.ethadien.yemeksiparisapp.data.entity.Food
import com.ethadien.yemeksiparisapp.data.repository.CartFoodRepository
import com.ethadien.yemeksiparisapp.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(var cartFoodRepository: CartFoodRepository): ViewModel() {

    fun addToCart(food : Food, count : Int){
        CoroutineScope(Dispatchers.Main).launch {
            cartFoodRepository.addToCart(
                food.food_name,
                food.food_image_name,
                food.food_price,
                count)
        }
    }
}