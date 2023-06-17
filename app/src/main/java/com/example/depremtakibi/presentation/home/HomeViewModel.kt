package com.example.depremtakibi.presentation.home

import android.util.Log
import androidx.lifecycle.*
import com.example.depremtakibi.common.Resource
import com.example.depremtakibi.data.mapper.HomeListMapperImpl
import com.example.depremtakibi.domain.use_cases.getAllEarthquakes.GetAllEarthquakesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllEarthquakesUseCase: GetAllEarthquakesUseCase,
    private val mapperImpl: HomeListMapperImpl,
) : ViewModel() {

    private val _earthquakeUiState = MutableLiveData<HomeUiState>()
    val earthquakeUiState: LiveData<HomeUiState> = _earthquakeUiState

    init {
        getEarthquakes()
    }

    fun getEarthquakes() {
        viewModelScope.launch {
            getAllEarthquakesUseCase().collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        _earthquakeUiState.postValue(HomeUiState.Loading)
                        Log.d("loadingg", "şu an loading te")
                    }

                    is Resource.Error -> {
                        _earthquakeUiState.postValue(HomeUiState.Error(it.exception.toString()))
                    }

                    is Resource.Success -> {
                        Log.d("successs", "successte, ${it.result?.get(0)?.title.toString()}")

                        _earthquakeUiState.postValue(HomeUiState.Success(mapperImpl.map(it.result!!)))

                    }
                }

            }
        }
    }
}