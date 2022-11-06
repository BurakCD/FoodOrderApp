package com.ethadien.yemeksiparisapp.ui.main.cart

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.ethadien.yemeksiparisapp.R
import com.ethadien.yemeksiparisapp.databinding.FragmentCartBinding
import com.ethadien.yemeksiparisapp.ui.main.home.CartDrinkAdapter
import com.ethadien.yemeksiparisapp.ui.main.home.RecyclerAdapter
import com.ethadien.yemeksiparisapp.utils.gate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        var totalPrice = 0

        binding.goToPaymentButton.setOnClickListener{
            Navigation.gate(requireView(), R.id.cart_to_payment)
        }

        with(viewModel) {
            cartList.observe(viewLifecycleOwner) {
                if (it != null) {
                    binding.cartRV.adapter = CartFoodAdapter(requireContext(), it!!, viewModel)
                    totalPrice = 0
                    for (i in it){
                        totalPrice += i.yemek_fiyat.toInt()
                    }
                    binding.cartTotalPrice.text = totalPrice.toString()
                }else{
                    binding.cartRV.adapter = EmptyCartAdapter(requireContext())
                }

            }
            drinkSet.observe(viewLifecycleOwner) {
                if (it != null) {
                    binding.cartDrinkRV.adapter = CartDrinkAdapter(requireContext(), it!!, viewModel)
                }
            }
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: CartViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCart()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}