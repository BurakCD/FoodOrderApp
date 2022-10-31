package com.ethadien.yemeksiparisapp.ui.login.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.ethadien.yemeksiparisapp.R
import com.ethadien.yemeksiparisapp.databinding.FragmentSignUpBinding
import com.ethadien.yemeksiparisapp.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: SignUpViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.suFragment = this
        initObservers()
    }

    private fun initObservers() {

        with(binding) {

            with(viewModel) {

                isInfosValid.observe(viewLifecycleOwner) {
                    if (it.not()) showSnackbar(
                        requireView(),
                        R.string.incomplete_information_entered
                    )
                }

                isValidMail.observe(viewLifecycleOwner) {
                    if (it.not()) {
                        mailSignUpInput.error = getString(R.string.invalid_mail)
                    } else {
                        mailSignUpInput.error = ""
                    }
                }

                isPasswordMatch.observe(viewLifecycleOwner) {
                    if (it.not()) {
                        passSignUpInput.error = getString(R.string.password_match_error)
                        confirmInput.error = getString(R.string.password_match_error)
                    } else {
                        passSignUpInput.error = ""
                        confirmInput.error = ""
                    }
                }

                isSignUp.observe(viewLifecycleOwner) {
                    if (it) {
                        showSnackbar(requireView(), R.string.sign_up_snack_text)
                        clearFields()
                    } else {
                        eMailSignUp.error = getString(R.string.registered_mail)
                    }
                }
            }
        }
    }

    fun signUpButton(
        eMail : String,
        password : String,
        confirmPassword : String,
        nickname : String,
        phoneNumber : String
    ){
        viewModel.signUp(eMail, password, confirmPassword, nickname, phoneNumber)
    }

    private fun clearFields(){
        with(binding){
            eMailSignUp.setText("")
            mailSignUpInput.error = ""
            passwordSignUp.setText("")
            passSignUpInput.error = ""
            confirmPassSignUp.setText("")
            confirmInput.error = ""
            nicknameSignUp.setText("")
            nicknameInput.error = ""
            phoneSignUp.setText("")
            phoneInput.error = ""
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}