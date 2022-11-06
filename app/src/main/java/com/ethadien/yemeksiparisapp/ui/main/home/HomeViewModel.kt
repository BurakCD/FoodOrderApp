package com.ethadien.yemeksiparisapp.ui.main.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
class HomeViewModel @Inject constructor(var foodRepo : FoodRepository, var cartFoodRepository: CartFoodRepository, var auth : FirebaseAuth) : ViewModel() {

    var foodSet = MutableLiveData<HashSet<Food>>()
    var drinkSet = MutableLiveData<HashSet<Food>>()
    var desertSet = MutableLiveData<HashSet<Food>>()

    init {
        getFoods()
    }

    fun getFoods() {
        CoroutineScope(Dispatchers.Main).launch {
            val foods = foodRepo.getFoods()
            var foodHash = HashSet<Food>()
            var drinkHash = HashSet<Food>()
            var desertHash = HashSet<Food>()


            for (i in foods){
                if (i.yemek_adi.equals("Ayran") || i.yemek_adi.equals("Su") ||
                    i.yemek_adi.equals("Kahve") || i.yemek_adi.equals("Fanta")){
                    drinkHash.add(i)
                } else if (i.yemek_adi.equals("Tiramisu") || i.yemek_adi.equals("Sütlaç") ||
                    i.yemek_adi.equals("Baklava") || i.yemek_adi.equals("Kadayıf")){
                    desertHash.add(i)
                }else{
                    foodHash.add(i)
                }
            }
            foodSet.value = foodHash
            desertSet.value = desertHash
            drinkSet.value = drinkHash
        }
    }

    fun addOneToCart(food : Food){
        CoroutineScope(Dispatchers.Main).launch {
            cartFoodRepository.addToCart(
                food.yemek_adi,
                food.yemek_resim_adi,
                food.yemek_fiyat,
                1, auth.currentUser!!.uid)
            Log.e("HomeViewModel", "addOneToCart")
        }
    }

}