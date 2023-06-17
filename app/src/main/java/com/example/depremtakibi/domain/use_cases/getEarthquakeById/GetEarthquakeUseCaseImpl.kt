package com.example.depremtakibi.domain.use_cases.getEarthquakeById

import com.example.depremtakibi.common.Resource
import com.example.depremtakibi.domain.model.EarthquakeDetailItem
import com.example.depremtakibi.domain.repository.EarthquakeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEarthquakeUseCaseImpl @Inject constructor(
    private val repository: EarthquakeRepository,
) : GetEarthquakeUseCase {
    override fun invoke(id: String): Flow<Resource<EarthquakeDetailItem>> = flow {
        emit(Resource.Loading)
        emit(repository.getEarthquake(id))
    }
}