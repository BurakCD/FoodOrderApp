package com.ethadien.yemeksiparisapp.ui.main.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ethadien.yemeksiparisapp.R
import com.ethadien.yemeksiparisapp.data.entity.Food
import com.ethadien.yemeksiparisapp.databinding.FragmentFoodDetailBinding
import com.ethadien.yemeksiparisapp.utils.Constants.IMAGE_BASE_URL
import com.mcdev.quantitizerlibrary.AnimationStyle
import com.mcdev.quantitizerlibrary.QuantitizerListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodDetailFragment : Fragment() {
    private lateinit var binding: FragmentFoodDetailBinding
    private lateinit var viewModel: FoodDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_detail, container, false)
        binding.foodDetailFragment = this

        val bundle: FoodDetailFragmentArgs by navArgs()
        val foodArg = bundle.food
        binding.foodInstance = foodArg

        getImage(foodArg.food_image_name)


        with(binding.quantitizer) {
            maxValue = 5
            setQuantitizerListener(object : QuantitizerListener {
                override fun onIncrease() {
                    var price = binding.priceText.text.toString().toInt() - foodArg.food_price

                    binding.priceText.text = price.toString()
                }

                override fun onValueChanged(value: Int) {
                }

                override fun onDecrease() {
                    var price = binding.priceText.text.toString().toInt() + foodArg.food_price
                    binding.priceText.text = price.toString()
                }

            })

            textAnimationStyle = AnimationStyle.SLIDE_IN_REVERSE

            setMinusIconColor(R.color.RED)
            setPlusIconColor(R.color.white)

            setPlusIconBackgroundColor(R.color.white)
            setMinusIconBackgroundColor(R.color.RED)
        }

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

    fun addToCart(food: Food) {
        viewModel.addToCart(food, binding.quantitizer.value)
    }
}