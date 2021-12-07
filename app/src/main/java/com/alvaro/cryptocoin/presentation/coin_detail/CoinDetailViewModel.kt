package com.alvaro.cryptocoin.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alvaro.cryptocoin.common.Constants
import com.alvaro.cryptocoin.common.Resource
import com.alvaro.cryptocoin.domain.interactors.get_coin.GetCoinDetails
import com.alvaro.cryptocoin.presentation.coin_list.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.jvm.Throws

@HiltViewModel
class CoinDetailViewModel
@Inject
constructor(
    private val getCoinDetails: GetCoinDetails,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _state = mutableStateOf(CoinDetailsState())
    val state: State<CoinDetailsState>
        get() = _state

    init {

        savedStateHandle.get<String>(Constants.COIN_ID_KEY)?.let{ coinId->
            requestCoinDetails( coinId )
        } ?: throw Exception("coin id was null")

    }

    private fun requestCoinDetails(coinId: String){
        getCoinDetails(coinId = coinId).onEach { result->
            when(result){
                is Resource.Success->{
                    _state.value = CoinDetailsState(coins = result.data)
                }
                is Resource.Error->{
                    _state.value = CoinDetailsState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading->{
                    _state.value = CoinDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}