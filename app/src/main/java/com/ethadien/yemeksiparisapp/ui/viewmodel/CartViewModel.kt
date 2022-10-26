package com.ethadien.yemeksiparisapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ethadien.yemeksiparisapp.data.entity.CartFood
import com.ethadien.yemeksiparisapp.data.repository.CartFoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor (var cartFoodRepo : CartFoodRepository) : ViewModel() {

    var cartList = MutableLiveData<List<CartFood>>()

    init {
        getCart()
    }

    fun getCart(user_name : String){
        CoroutineScope(Dispatchers.Main).launch {
            cartList.value = cartFoodRepo.getCart(user_name)
        }
    }

    fun delete(cart_food_id : Int, user_name : String){
        CoroutineScope(Dispatchers.Main).launch {
            cartFoodRepo.delete(cart_food_id, user_name)
        }
    }
}