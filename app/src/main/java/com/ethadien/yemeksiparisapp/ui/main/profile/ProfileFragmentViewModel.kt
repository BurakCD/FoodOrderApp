package com.ethadien.yemeksiparisapp.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ethadien.yemeksiparisapp.data.entity.UserModel
import com.ethadien.yemeksiparisapp.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileFragmentViewModel @Inject constructor (var usersRepo : UserRepository) : ViewModel() {


    private var _userInfo = MutableLiveData<UserModel>()
    val userInfo: LiveData<UserModel>
        get() = _userInfo

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        usersRepo.getUserInfo()
        _userInfo = usersRepo.userModelInfo
    }

    fun signOut() {
        usersRepo.signOut()
    }

}