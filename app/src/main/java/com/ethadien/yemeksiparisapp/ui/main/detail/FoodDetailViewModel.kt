package com.ethadien.yemeksiparisapp.ui.main.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ethadien.yemeksiparisapp.data.entity.food.Food
import com.ethadien.yemeksiparisapp.data.repository.CartFoodRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(var cartFoodRepository: CartFoodRepository, var firebaseAuth : FirebaseAuth): ViewModel() {

    fun addToCart(food_name : String, food_image_name:String, food_price:Int, count : Int){
        CoroutineScope(Dispatchers.Main).launch {
            cartFoodRepository.addToCart(
                food_name,
                food_image_name,
                food_price,
                count, firebaseAuth.currentUser!!.uid)
            Log.e("detailViewModel", "addToCart")
        }
    }
}