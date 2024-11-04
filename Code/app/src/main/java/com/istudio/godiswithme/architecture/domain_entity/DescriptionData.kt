package com.istudio.godiswithme.architecture.domain_entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DescriptionData(
    @SerialName("meta-data") val metaData: MetaData,
    val data: List<ContentData>
)
