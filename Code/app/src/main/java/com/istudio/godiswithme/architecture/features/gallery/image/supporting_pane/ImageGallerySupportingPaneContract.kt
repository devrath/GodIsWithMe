package com.istudio.godiswithme.architecture.features.gallery.image.supporting_pane

import android.graphics.Bitmap
import com.istudio.godiswithme.architecture.domain_entity.GodData

interface ImageGallerySupportingPaneContract {
    //data class UiState(val count: Int)
    data class UiState(
        val isLoading: Boolean = true,
        val godData: GodData? = null,
    )

    sealed interface UiAction {
        data class LoadScreen(val godName: String) : UiAction
        //data object OnDecreaseCountClick : UiAction
    }

    sealed interface SideEffect {
        data object DisplayError : SideEffect
    }
}