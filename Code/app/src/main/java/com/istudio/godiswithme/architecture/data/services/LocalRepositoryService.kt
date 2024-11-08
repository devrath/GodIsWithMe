package com.istudio.godiswithme.architecture.data.services

import com.istudio.godiswithme.architecture.domain.models.GodData
import com.istudio.godiswithme.architecture.domain.models.Song
import kotlinx.coroutines.flow.Flow

interface LocalRepositoryService {
    fun getGodData(godName: String, languageCode: String) : Flow<GodData?>
    fun getGodList(languageCode: String) : Flow<List<GodData>>
    fun getAudioList(godName: String,languageCode: String) : Flow<List<Song>>
}