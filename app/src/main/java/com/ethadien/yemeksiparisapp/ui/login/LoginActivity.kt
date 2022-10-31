package com.ethadien.yemeksiparisapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ethadien.yemeksiparisapp.R
import com.ethadien.yemeksiparisapp.databinding.ActivityLoginBinding
import com.ethadien.yemeksiparisapp.ui.main.MainActivity
import com.ethadien.yemeksiparisapp.utils.Constants.SIGN_IN
import com.ethadien.yemeksiparisapp.utils.Constants.SIGN_UP
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        Firebase.auth.currentUser?.let {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val titleList = arrayListOf(SIGN_IN, SIGN_UP)

        binding.viewPager.adapter = LoginPagerAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) {tab, position ->
            tab.text = titleList[position]
        }.attach()
    }
}