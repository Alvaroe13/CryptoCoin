package com.alvaro.cryptocoin.di

import com.alvaro.cryptocoin.common.Constants
import com.alvaro.cryptocoin.data.remote.CoinPaprikaApi
import com.alvaro.cryptocoin.data.repository.CoinRepositoryImpl
import com.alvaro.cryptocoin.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideCoinPaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api : CoinPaprikaApi): CoinRepository{
        return CoinRepositoryImpl(api = api)
    }
}