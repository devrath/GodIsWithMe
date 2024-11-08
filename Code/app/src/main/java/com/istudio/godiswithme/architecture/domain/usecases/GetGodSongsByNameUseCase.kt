package com.istudio.godiswithme.architecture.domain.usecases

import com.istudio.godiswithme.architecture.domain.models.Song
import com.istudio.godiswithme.architecture.domain.repository.GodRepository
import com.istudio.godiswithme.common.usecases.UseCaseFlowWithParam
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGodSongsByNameUseCase @Inject constructor(
    private val repository: GodRepository
) : UseCaseFlowWithParam<String, List<Song>> {

    override fun invoke(param: String): Flow<List<Song>> = repository.getAudioList(
        godName = param, languageCode = "en"
    )

}
