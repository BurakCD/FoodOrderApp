package com.ethadien.yemeksiparisapp.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ethadien.yemeksiparisapp.data.entity.Food
import com.ethadien.yemeksiparisapp.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var foodRepo : FoodRepository) : ViewModel() {

    var foodList = MutableLiveData<List<Food>>()

    init {
        getFoods()
    }

    fun getFoods() {
        CoroutineScope(Dispatchers.Main).launch {
            foodList.value = foodRepo.getFoods()
        }
    }

}