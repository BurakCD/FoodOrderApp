package com.ethadien.yemeksiparisapp.retrofit

import com.ethadien.yemeksiparisapp.data.entity.CartFoodResponse
import com.ethadien.yemeksiparisapp.data.entity.CrudAnswer
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface CartFoodDao {

    @GET("sepettekiYemekleriGetir.php")
    suspend fun getCart(
        @Field("kullanici_adi") user_name: String
    ) : CartFoodResponse

    @POST("sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun save(
        @Field("yemek_adi") food_name : String,
        @Field("yemek_resim_adi") food_image_name : String,
        @Field("yemek_fiyat") food_price : Int,
        @Field("yemek_siparis_adet") food_count : Int,
        @Field("kullanici_adi") user_name : String) : CrudAnswer

    @POST("sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun delete(
        @Field("sepet_yemek_id") cart_food_id : Int,
        @Field("kullanici_adi") user_name : String
    ) : CrudAnswer

}