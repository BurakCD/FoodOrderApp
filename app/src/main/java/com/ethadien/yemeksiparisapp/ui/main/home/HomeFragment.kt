package com.ethadien.yemeksiparisapp.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ethadien.yemeksiparisapp.R
import com.ethadien.yemeksiparisapp.databinding.FragmentHomeBinding
import com.ethadien.yemeksiparisapp.utils.gate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.homeFragment = this


        with(viewModel) {
            foodSet.observe(viewLifecycleOwner) {
                if (it != null) {
                    binding.foodsRV.adapter = RecyclerAdapter(requireContext(), it, viewModel)

                    //binding.homeRV.layoutManager =
                        //StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    //binding.homeRV.adapter = FoodAdapter(requireContext(), it, viewModel)
                }
            }
            drinkSet.observe(viewLifecycleOwner){
                if (it != null) {
                    binding.drinkRV.adapter = RecyclerAdapter(requireContext(), it, viewModel)
                }
            }
            desertSet.observe(viewLifecycleOwner){
                if (it != null) {
                    binding.desertRV.adapter = RecyclerAdapter(requireContext(), it, viewModel)
                }
            }
        }
        return binding.root
    }

    fun goToProfile(){
        Navigation.gate(requireView(), R.id.home_to_profile)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : HomeViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFoods()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
