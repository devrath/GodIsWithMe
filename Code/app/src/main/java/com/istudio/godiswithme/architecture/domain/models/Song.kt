package com.istudio.godiswithme.architecture.domain.models

import android.net.Uri
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.net.URI

@Serializable
data class Song(
    val songName: String,
    var songLocation: String
)
