package com.alvaro.cryptocoin.data.remote

import com.alvaro.cryptocoin.data.remote.dto.CoinDetailDto
import com.alvaro.cryptocoin.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinDetails(@Path("coinId") coinId: String): CoinDetailDto

}