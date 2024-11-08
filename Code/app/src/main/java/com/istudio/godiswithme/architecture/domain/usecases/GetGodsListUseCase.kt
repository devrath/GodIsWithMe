package com.istudio.godiswithme.architecture.domain.usecases

import com.istudio.godiswithme.architecture.domain.models.GodData
import com.istudio.godiswithme.architecture.domain.repository.GodRepository
import com.istudio.godiswithme.common.usecases.UseCaseFlow
import com.istudio.godiswithme.common.usecases.UseCaseFlowWithParam
import kotlinx.coroutines.flow.Flow

class GetGodsListUseCase(
    private val repository: GodRepository
) : UseCaseFlowWithParam<GetGodsListUseCase.Param,List<GodData>> {

    override fun invoke(param: Param): Flow<List<GodData>> {
        return repository.getGodList(languageCode = param.languageCode)
    }

    data class Param(val languageCode: String)
}

