package com.example.depremtakibi.presentation.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.depremtakibi.common.Resource
import com.example.depremtakibi.data.mapper.DetailsMapperImpl
import com.example.depremtakibi.domain.use_cases.getEarthquakeById.GetEarthquakeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getEarthquakeUseCase: GetEarthquakeUseCase,
    private val mapperDetail: DetailsMapperImpl,
) : ViewModel() {

    private val _detailUiState = MutableLiveData<DetailsUiState>()
    val detailUiState: LiveData<DetailsUiState> get() = _detailUiState


    fun getEartquakeById(id: String) {
        viewModelScope.launch {
            getEarthquakeUseCase(id).collect {
                when (it) {
                    is Resource.Loading -> {
                        _detailUiState.postValue(DetailsUiState.Loading)
                    }

                    is Resource.Error -> {
                        _detailUiState.postValue(DetailsUiState.Error(it.exception.toString()))
                        Log.d("viewmodel_error", it.exception!!)
                    }

                    is Resource.Success -> {
                        _detailUiState.postValue(DetailsUiState.Success(mapperDetail.map(it.result!!)))
                        Log.d("viewmodel_id", it.result.earthquake_id)
                    }


                }
            }
        }

    }


}