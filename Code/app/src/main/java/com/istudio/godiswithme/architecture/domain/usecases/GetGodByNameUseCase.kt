package com.istudio.godiswithme.architecture.domain.usecases

import com.istudio.godiswithme.architecture.domain.models.GodData
import com.istudio.godiswithme.architecture.domain.repository.GodRepository
import com.istudio.godiswithme.common.usecases.UseCaseFlowWithParam
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGodByNameUseCase @Inject constructor(
    private val repository: GodRepository
) : UseCaseFlowWithParam<String, GodData?> {

    override fun invoke(param: String): Flow<GodData?> = repository.getGodData(
        godName = param, languageCode = "en"
    )

}
