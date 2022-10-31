package com.ethadien.yemeksiparisapp.ui.main.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ethadien.yemeksiparisapp.data.entity.CartFood
import com.ethadien.yemeksiparisapp.data.repository.CartFoodRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor (var cartFoodRepo : CartFoodRepository, var firebaseAuth: FirebaseAuth) : ViewModel() {

    var cartList = MutableLiveData<List<CartFood>>()

    init {
        getCart()
    }

    fun getCart(){
        CoroutineScope(Dispatchers.Main).launch {
            cartList.value = cartFoodRepo.getCart(firebaseAuth.currentUser!!.uid)
        }
    }

    fun delete(cart_food_id : Int){
        CoroutineScope(Dispatchers.Main).launch {
            cartFoodRepo.deleteFromCart(cart_food_id, firebaseAuth.currentUser!!.uid)
        }
    }
}