package com.ethadien.yemeksiparisapp.ui.login.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ethadien.yemeksiparisapp.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(var userRepository: UserRepository) : ViewModel() {

    private var _isSignIn = MutableLiveData<Boolean>()

    val isSignIn : LiveData<Boolean>
    get() = _isSignIn

    init {
        _isSignIn = userRepository.isSignIn
    }

    fun signIn(e_mail : String, password : String){
        userRepository.signIn(e_mail, password)
    }

}