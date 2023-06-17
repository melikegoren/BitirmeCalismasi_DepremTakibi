package com.example.depremtakibi.presentation.detail

sealed class DetailsUiState {
    object Loading : DetailsUiState()
    data class Success(val data: DetailsUiData) : DetailsUiState()
    data class Error(val message: String) : DetailsUiState()
}