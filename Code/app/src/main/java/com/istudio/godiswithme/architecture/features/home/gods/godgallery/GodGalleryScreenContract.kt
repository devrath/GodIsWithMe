package com.istudio.godiswithme.architecture.features.home.gods.godgallery

import android.graphics.Bitmap
import com.istudio.godiswithme.architecture.domain.models.GodData

interface GodGalleryScreenContract {
    data class UiState(
        val isLoading: Boolean = true,
        val listOfGods: List<GodData> = emptyList(),
        val listOfGodImages: List<Bitmap> = emptyList(),
    )

    sealed interface UiAction {
        //data object OnIncreaseCountClick : UiAction
        //data object OnDecreaseCountClick : UiAction
    }

    sealed interface SideEffect {
        data object DisplayError : SideEffect
    }
}