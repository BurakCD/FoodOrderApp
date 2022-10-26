package com.ethadien.yemeksiparisapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object{
        fun getClient(BaseUrl : String) : Retrofit {
            return Retrofit
                .Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}