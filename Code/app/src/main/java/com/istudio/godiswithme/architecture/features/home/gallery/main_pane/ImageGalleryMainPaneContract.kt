package com.istudio.godiswithme.architecture.features.home.gallery.main_pane

import android.graphics.Bitmap
import com.istudio.godiswithme.architecture.domain_entity.GodData

interface ImageGalleryMainPaneContract {
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