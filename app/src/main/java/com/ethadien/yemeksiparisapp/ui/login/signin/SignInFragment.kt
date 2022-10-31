package com.ethadien.yemeksiparisapp.ui.login.signin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.ethadien.yemeksiparisapp.R
import com.ethadien.yemeksiparisapp.databinding.FragmentSignInBinding
import com.ethadien.yemeksiparisapp.ui.main.MainActivity
import com.ethadien.yemeksiparisapp.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel : SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: SignInViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.siFragment = this

        with(viewModel) {

            isSignIn.observe(viewLifecycleOwner) {
                if (it) {
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    showSnackbar(view, R.string.wrong_email_password)
                }
            }
        }
    }

    fun signInButton(e_mail : String, password : String){
        viewModel.signIn(e_mail, password)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}