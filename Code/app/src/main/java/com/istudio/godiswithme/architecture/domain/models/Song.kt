package com.istudio.godiswithme.architecture.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Song(
    val songName: String,
    val songLocation: String
)
