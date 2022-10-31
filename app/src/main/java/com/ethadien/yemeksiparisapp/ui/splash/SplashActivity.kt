package com.ethadien.yemeksiparisapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.ethadien.yemeksiparisapp.R
import com.ethadien.yemeksiparisapp.databinding.ActivitySplashBinding
import com.ethadien.yemeksiparisapp.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val timer = object : CountDownTimer(4000, 10){
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                goToLogin()
                finish()
            }
        }.start()

     }

    fun goToLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}