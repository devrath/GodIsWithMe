package com.istudio.godiswithme.architecture.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetaData(
    @SerialName("Supported-languages") val supportedLanguages: List<SupportedLanguage>,
    @SerialName("GodName") val godName: String
)

