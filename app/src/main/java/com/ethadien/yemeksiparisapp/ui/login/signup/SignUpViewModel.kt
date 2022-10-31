package com.ethadien.yemeksiparisapp.ui.login.signup

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ethadien.yemeksiparisapp.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(var userRepository: UserRepository) : ViewModel(){

    private var _isInfosValid = MutableLiveData<Boolean>()
    val isInfosValid : LiveData<Boolean> = _isInfosValid

    private var _isValidMail = MutableLiveData<Boolean>()
    val isValidMail : LiveData<Boolean> = _isValidMail

    private var _isPasswordMatch = MutableLiveData<Boolean>()
    val isPasswordMatch : LiveData<Boolean> = _isPasswordMatch

    private var _isSignUp = MutableLiveData<Boolean>()
    val isSignUp : LiveData<Boolean> = _isSignUp

    init {

    }

    fun signUp(
        e_mail : String,
        password : String,
        confirmPassword : String,
        nickname : String,
        phoneNumber : String
    ){
        if (e_mail.isEmpty() ||
            password.isEmpty() ||
            confirmPassword.isEmpty() ||
            nickname.isEmpty() ||
            phoneNumber.isEmpty()){
            _isInfosValid.value = true
        }else{
            if (Patterns.EMAIL_ADDRESS.matcher(e_mail).matches().not()){
                _isValidMail.value = false
            }else{
                if (password != confirmPassword){
                    _isPasswordMatch.value = false
                }else{
                    userRepository.signUp(e_mail, password, nickname, phoneNumber)
                }
            }
        }
    }
}