package com.alvaro.cryptocoin.domain.interactors.get_coins

import com.alvaro.cryptocoin.common.ViewState
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

    operator fun invoke(): Flow<ViewState<List<Coin>>> = flow{
        try {
            emit(ViewState.Loading())
            val coin = repository.getCoins().map { it.toCoin() }
            emit(ViewState.Success(coin))
        } catch(e: HttpException) {
            emit(ViewState.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(ViewState.Error("Couldn't reach server. Check your internet connection."))
        }
    }

}