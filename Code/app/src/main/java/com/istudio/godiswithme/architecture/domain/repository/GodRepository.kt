package com.istudio.godiswithme.architecture.domain.repository

import com.istudio.godiswithme.architecture.domain.models.GodData
import kotlinx.coroutines.flow.Flow

interface GodRepository {
    fun getGodData(godName: String) : Flow<GodData?>
    fun getGodList(languageCode: String) : Flow<List<GodData>>
}