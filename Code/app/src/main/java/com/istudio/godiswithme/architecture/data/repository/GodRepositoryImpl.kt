package com.istudio.godiswithme.architecture.data.repository

import com.istudio.godiswithme.architecture.data.services.localdata.LocalRepositoryService
import com.istudio.godiswithme.architecture.domain.repository.GodRepository

class GodRepositoryImpl(
    private val localRepositoryService: LocalRepositoryService
) : GodRepository {

    override fun getGodData(godName: String, languageCode: String) =
        localRepositoryService.getGodData(godName, languageCode)

    override fun getGodList(languageCode: String) = localRepositoryService.getGodList(languageCode)

    override fun getAudioList(godName: String, languageCode: String) =
        localRepositoryService.getAudioList(godName, languageCode)

}