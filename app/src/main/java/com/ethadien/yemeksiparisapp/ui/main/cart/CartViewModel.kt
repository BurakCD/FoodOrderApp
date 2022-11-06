package com.ethadien.yemeksiparisapp.ui.main.cart

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ethadien.yemeksiparisapp.data.entity.cart.CartFood
import com.ethadien.yemeksiparisapp.data.entity.food.Food
import com.ethadien.yemeksiparisapp.data.repository.CartFoodRepository
import com.ethadien.yemeksiparisapp.data.repository.FoodRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor (var cartFoodRepo : CartFoodRepository, var foodRepository: FoodRepository, var firebaseAuth: FirebaseAuth) : ViewModel() {

    var cartList = MutableLiveData<List<CartFood>>()!!
    var drinkSet = MutableLiveData<HashSet<Food>>()

    init {
        getCart()
        getDrinks()
    }

    fun getCart(){
        CoroutineScope(Dispatchers.Main).launch {
            cartList.value = cartFoodRepo.getCart(firebaseAuth.currentUser!!.uid)
            Log.e("Adım", "1")
        }
    }

    fun getDrinks(){
        CoroutineScope(Dispatchers.Main).launch {
            val foods = foodRepository.getFoods()
            var drinkHash = HashSet<Food>()

            for (i in foods) {
                if (i.yemek_adi.equals("Ayran") || i.yemek_adi.equals("Su") ||
                    i.yemek_adi.equals("Kahve") || i.yemek_adi.equals("Fanta")
                ) {
                    drinkHash.add(i)
                }
            }
            drinkSet.value = drinkHash
        }
    }

    fun addOneToCart(food : Food){
        CoroutineScope(Dispatchers.Main).launch {
            cartFoodRepo.addToCart(
                food.yemek_adi,
                food.yemek_resim_adi,
                food.yemek_fiyat,
                1, firebaseAuth.currentUser!!.uid)
            Log.e("HomeViewModel", "addOneToCart")
            getCart()
        }
    }

    fun delete(cart_food_id : Int){
        CoroutineScope(Dispatchers.Main).launch {
            cartFoodRepo.deleteFromCart(cart_food_id, firebaseAuth.currentUser!!.uid)
            getCart()
        }
    }
}