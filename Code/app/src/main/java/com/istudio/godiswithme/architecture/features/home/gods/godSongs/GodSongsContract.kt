package com.istudio.godiswithme.architecture.features.home.gods.godSongs

import com.istudio.godiswithme.architecture.domain.models.GodData

interface GodSongsContract {
    data class UiState(
        val isLoading: Boolean = true,
        val godData: GodData? = null,
    )

    sealed interface UiAction {
        data class LoadScreen(val godName: String) : UiAction
    }

    sealed interface SideEffect {
        data object DisplayError : SideEffect
    }
}