package com.alvaro.cryptocoin.domain.repository

import com.alvaro.cryptocoin.data.remote.dto.CoinDetailDto
import com.alvaro.cryptocoin.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinDetails(coinId: String): CoinDetailDto

}