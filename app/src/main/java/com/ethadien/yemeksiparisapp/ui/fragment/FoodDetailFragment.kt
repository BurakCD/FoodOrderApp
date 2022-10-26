package com.ethadien.yemeksiparisapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.ethadien.yemeksiparisapp.R
import com.ethadien.yemeksiparisapp.databinding.FragmentFoodDetailBinding
import com.ethadien.yemeksiparisapp.databinding.FragmentHomeBinding
import com.ethadien.yemeksiparisapp.ui.viewmodel.FoodDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodDetailFragment : Fragment() {
    private lateinit var binding : FragmentFoodDetailBinding
    private lateinit var viewModel : FoodDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_detail, container, false)


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:FoodDetailViewModel by viewModels()
        viewModel = tempViewModel
    }
}