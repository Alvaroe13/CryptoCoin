package com.alvaro.cryptocoin.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alvaro.cryptocoin.common.Resource
import com.alvaro.cryptocoin.domain.interactors.get_coins.GetCoinList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel
@Inject
constructor(
    private val getCoinList: GetCoinList
): ViewModel() {


    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState>
        get() = _state

    init {
        requestCoinList()
    }


    private fun requestCoinList(){
        getCoinList().onEach { result->
            when(result){
                is Resource.Success->{
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error->{
                    _state.value = CoinListState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading->{
                    _state.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}