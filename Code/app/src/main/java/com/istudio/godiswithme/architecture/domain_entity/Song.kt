package com.istudio.godiswithme.architecture.domain_entity

import kotlinx.serialization.Serializable

@Serializable
data class Song(
    val songName: String,
    val songLocation: String
)
