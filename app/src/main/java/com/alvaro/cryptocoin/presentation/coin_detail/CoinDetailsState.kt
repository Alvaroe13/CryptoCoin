package com.alvaro.cryptocoin.presentation.coin_detail

import com.alvaro.cryptocoin.domain.model.CoinDetail

data class CoinDetailsState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
