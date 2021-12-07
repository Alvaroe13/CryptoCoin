package com.alvaro.cryptocoin.common

sealed class ViewState<out T>{
    data class Success<out T> (val data : T) : ViewState<T>()
    data class Error( val message : String) : ViewState<Nothing>()
    data class Loading(val isLoading: Boolean = false) : ViewState<Nothing>()
    object Idle : ViewState<Nothing>()
}
