package com.cth.horoscopoapp.ui.detail

sealed class HoroscopeDetailState {

    data object Loading : HoroscopeDetailState()

    //El data Clase se usa cunado se pasa un dato o un valor
    data class Error(val error: String) : HoroscopeDetailState()
    data class Succes(val data: String) : HoroscopeDetailState()
}