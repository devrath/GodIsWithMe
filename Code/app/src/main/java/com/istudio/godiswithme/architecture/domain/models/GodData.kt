package com.istudio.godiswithme.architecture.domain.models

import android.graphics.Bitmap
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class GodData(
    val godName: String,
    val language: String,
    @SerialName("language-code") val languageCode: String,
    val description: String,
    @Transient var godImageUri: String? = null,
    @Transient var godImageBitmap: Bitmap? = null
)
