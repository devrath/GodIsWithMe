package com.istudio.godiswithme.architecture.domain.usecases

import com.istudio.godiswithme.architecture.domain.models.GodData
import com.istudio.godiswithme.architecture.domain.repository.GodRepository
import com.istudio.godiswithme.common.usecases.UseCaseFlow
import kotlinx.coroutines.flow.Flow

class GetGodsListUseCase(
    private val repository: GodRepository
) : UseCaseFlow<List<GodData>> {

    override fun invoke(): Flow<List<GodData>> = repository.getGodList(languageCode = "en")

}

