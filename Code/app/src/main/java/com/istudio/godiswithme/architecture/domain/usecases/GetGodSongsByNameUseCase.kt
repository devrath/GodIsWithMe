package com.istudio.godiswithme.architecture.domain.usecases

import com.istudio.godiswithme.architecture.domain.models.Song
import com.istudio.godiswithme.architecture.domain.repository.GodRepository
import com.istudio.godiswithme.common.usecases.UseCaseFlowWithParam
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGodSongsByNameUseCase @Inject constructor(
    private val repository: GodRepository
) : UseCaseFlowWithParam<GetGodSongsByNameUseCase.Param, List<Song>> {

    override fun invoke(param: Param): Flow<List<Song>> {
        return repository.getAudioList(
            godName = param.godName, languageCode = param.languageCode
        )
    }

    data class Param(val godName: String, val languageCode: String)
}
