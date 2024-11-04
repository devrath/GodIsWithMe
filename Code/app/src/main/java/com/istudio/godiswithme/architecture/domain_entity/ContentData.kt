package com.istudio.godiswithme.architecture.domain_entity

import android.graphics.Bitmap
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class ContentData(
    val language: String,
    @SerialName("language-code") val languageCode: String,
    val description: String,
    @Transient var imageBitmap: Bitmap? = null
)
