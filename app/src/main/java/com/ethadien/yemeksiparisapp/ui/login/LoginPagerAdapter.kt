package com.ethadien.yemeksiparisapp.ui.login

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ethadien.yemeksiparisapp.ui.login.signin.SignInFragment
import com.ethadien.yemeksiparisapp.ui.login.signup.SignUpFragment

class LoginPagerAdapter(fragmentManager: FragmentManager, lifeCycle : Lifecycle) :
FragmentStateAdapter(fragmentManager, lifeCycle)
{
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0-> return SignInFragment()
            1-> return SignUpFragment()
        }
        return SignInFragment()
    }
}