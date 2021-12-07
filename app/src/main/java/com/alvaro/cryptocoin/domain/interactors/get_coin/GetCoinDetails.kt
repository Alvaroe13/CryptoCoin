package com.alvaro.cryptocoin.domain.interactors.get_coin

import com.alvaro.cryptocoin.common.ViewState
import com.alvaro.cryptocoin.data.remote.dto.toCoinDetail
import com.alvaro.cryptocoin.domain.model.CoinDetail
import com.alvaro.cryptocoin.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinDetails
@Inject
constructor(
    private val repository: CoinRepository
){


    operator fun invoke(coinId: String): Flow<ViewState<CoinDetail>> = flow{
        try {
            emit(ViewState.Loading())
            val coin = repository.getCoinDetails(coinId).toCoinDetail()
            emit(ViewState.Success(coin))
        } catch(e: HttpException) {
            emit(ViewState.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(ViewState.Error("Couldn't reach server. Check your internet connection."))
        }
    }


}