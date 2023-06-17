package com.example.depremtakibi.presentation.home


sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val data: List<HomeUiData>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}