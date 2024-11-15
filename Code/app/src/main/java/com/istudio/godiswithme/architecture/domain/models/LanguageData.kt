package com.istudio.godiswithme.architecture.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LanguageData(
    @SerialName("language") val language: String,
    @SerialName("language-code") val languageCode: String,
    val songs: List<Song>,
    val description: String
)
