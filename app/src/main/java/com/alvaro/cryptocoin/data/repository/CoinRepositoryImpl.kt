package com.alvaro.cryptocoin.data.repository

import com.alvaro.cryptocoin.data.remote.CoinPaprikaApi
import com.alvaro.cryptocoin.data.remote.dto.CoinDetailDto
import com.alvaro.cryptocoin.data.remote.dto.CoinDto
import com.alvaro.cryptocoin.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl
@Inject
constructor(
    private val api : CoinPaprikaApi
): CoinRepository{

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinDetails(coinId: String): CoinDetailDto {
        return api.getCoinDetails(coinId = coinId)
    }
}