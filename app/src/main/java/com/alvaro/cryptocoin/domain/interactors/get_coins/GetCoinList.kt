package com.alvaro.cryptocoin.domain.interactors.get_coins

import com.alvaro.cryptocoin.common.Resource
import com.alvaro.cryptocoin.data.remote.dto.toCoin
import com.alvaro.cryptocoin.domain.model.Coin
import com.alvaro.cryptocoin.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinList
@Inject
constructor(
    private val repository: CoinRepository
){

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow{
        try {
            emit(Resource.Loading)
            val coin = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coin))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

}