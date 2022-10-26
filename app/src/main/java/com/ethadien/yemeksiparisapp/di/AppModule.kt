package com.ethadien.yemeksiparisapp.di

import com.ethadien.yemeksiparisapp.data.datasource.CartFoodDataSource
import com.ethadien.yemeksiparisapp.data.datasource.FoodDataSource
import com.ethadien.yemeksiparisapp.data.repository.CartFoodRepository
import com.ethadien.yemeksiparisapp.data.repository.FoodRepository
import com.ethadien.yemeksiparisapp.retrofit.ApiUtils
import com.ethadien.yemeksiparisapp.retrofit.CartFoodDao
import com.ethadien.yemeksiparisapp.retrofit.FoodDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    /**
     * FOOD PROVIDERS
     */

    @Provides
    @Singleton
    fun provideFoodRepository(foodDS : FoodDataSource) : FoodRepository{
        return FoodRepository(foodDS)
    }

    @Provides
    @Singleton
    fun provideFoodDataSource(foodDao : FoodDao) : FoodDataSource{
        return FoodDataSource(foodDao)
    }

    @Provides
    @Singleton
    fun provideFoodDao() : FoodDao{
        return ApiUtils.getFoodDao()
    }

    /**
     * CART FOOD PROVIDERS
     */

    @Provides
    @Singleton
    fun provideCartFoodRepository(cartFoodDS : CartFoodDataSource) : CartFoodRepository {
        return CartFoodRepository(cartFoodDS)
    }

    @Provides
    @Singleton
    fun provideCartFoodDataSource(cartFoodDao : CartFoodDao) : CartFoodDataSource{
        return CartFoodDataSource(cartFoodDao)
    }

    @Provides
    @Singleton
    fun provideCartFoodDao() : CartFoodDao {
        return ApiUtils.getCartFoodDao()
    }
}
