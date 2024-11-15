package com.istudio.godiswithme.architecture.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SupportedLanguage(
    @SerialName("language") val language: String,
    @SerialName("language-code") val languageCode: String,
    @SerialName("god-name") val godName: String
)
