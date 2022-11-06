package com.ethadien.yemeksiparisapp.ui.main.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ethadien.yemeksiparisapp.R
import com.ethadien.yemeksiparisapp.databinding.FragmentProfileBinding
import com.ethadien.yemeksiparisapp.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel : ProfileFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileFragment = this

        with(binding) {

            signOutButton.setOnClickListener {
                viewModel.signOut().also {
                    val intent = Intent(requireActivity(), LoginActivity::class.java)
                    startActivity(intent)
                }
            }

            viewModel.userInfo.observe(viewLifecycleOwner) {
                emailText.text = it.e_mail
                nicknameText.text = it.nickname
                phoneNumberText.text = it.phone
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ProfileFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}