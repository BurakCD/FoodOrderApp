package com.ethadien.yemeksiparisapp.ui.main.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ethadien.yemeksiparisapp.R
import com.ethadien.yemeksiparisapp.data.entity.food.Food
import com.ethadien.yemeksiparisapp.databinding.FragmentFoodDetailBinding
import com.ethadien.yemeksiparisapp.utils.Constants.IMAGE_BASE_URL
import com.ethadien.yemeksiparisapp.utils.gate
import com.ethadien.yemeksiparisapp.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodDetailFragment : Fragment() {
    private var _binding: FragmentFoodDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FoodDetailViewModel
    private lateinit var foodInstance : Food

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_detail, container, false)
        binding.foodDetailFragment = this

        val bundle: FoodDetailFragmentArgs by navArgs()
        val foodArg = bundle.food
        foodInstance = foodArg
        binding.foodInstance = foodArg

        getImage(foodArg.yemek_resim_adi)


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FoodDetailViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun getImage(imageUrl: String) {
        Glide.with(this)
            .load("$IMAGE_BASE_URL$imageUrl")
            .override(220, 220)
            .into(binding.foodImage)
    }

    fun decreaseCount(){
        val count = binding.countText.text.toString().toInt() -1
        val price = binding.priceText.text.toString().toInt() - foodInstance.yemek_fiyat
        if (count > 0){
            binding.countText.text = count.toString()
            binding.priceText.text = price.toString()
        }else{
            showSnackbar(requireView(), R.string.not_zero)
        }
        if (binding.priceText.text.toString().toInt() < foodInstance.yemek_fiyat){
            binding.priceText.text = foodInstance.yemek_fiyat.toString()
        }

    }

    fun increaseCount(){
        val count = binding.countText.text.toString().toInt() + 1
        binding.countText.text = count.toString()
        val price = binding.priceText.text.toString().toInt() + foodInstance.yemek_fiyat
        binding.priceText.text = price.toString()
    }

    fun addToCart(food: Food, count : String) {
        viewModel.addToCart(food, count.toInt())
    }

    fun goToHome(){
        Navigation.gate(requireView(), R.id.go_to_home)
        onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}