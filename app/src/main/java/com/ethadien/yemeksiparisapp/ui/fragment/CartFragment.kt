package com.ethadien.yemeksiparisapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.ethadien.yemeksiparisapp.R
import com.ethadien.yemeksiparisapp.databinding.FragmentCartBinding
import com.ethadien.yemeksiparisapp.databinding.FragmentHomeBinding
import com.ethadien.yemeksiparisapp.ui.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class CartFragment : Fragment() {
    private lateinit var binding : FragmentCartBinding
    private lateinit var viewModel : CartViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:CartViewModel by viewModels()
        viewModel = tempViewModel
    }
}