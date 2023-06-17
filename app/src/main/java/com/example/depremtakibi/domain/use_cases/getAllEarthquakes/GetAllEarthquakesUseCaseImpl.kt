package com.example.depremtakibi.domain.use_cases.getAllEarthquakes

import com.example.depremtakibi.common.Resource
import com.example.depremtakibi.domain.repository.EarthquakeRepository
import com.example.depremtakibi.domain.model.EarthquakeItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllEarthquakesUseCaseImpl @Inject constructor(
    private val repository: EarthquakeRepository,
) : GetAllEarthquakesUseCase {

    override fun invoke(): Flow<Resource<List<EarthquakeItem>>> = flow {
        emit(Resource.Loading)
        emit(repository.getEarthquakes() as Resource<List<EarthquakeItem>>)
    }
}